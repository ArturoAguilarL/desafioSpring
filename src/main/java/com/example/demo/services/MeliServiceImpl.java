package com.example.demo.services;

import com.example.demo.dto.*;
import com.example.demo.exceptions.BadRequestExceedsNumberOfFilters;
import com.example.demo.exceptions.BadRequestTypeOrderInvalid;
import com.example.demo.exceptions.ProductIdNotFound;
import com.example.demo.exceptions.ProductNoStock;
import com.example.demo.repositories.MeliRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MeliServiceImpl implements MeliService {
    @Autowired
    private final MeliRepository meliRepository;
    private final Map<Integer, Integer> requestMap;

    public MeliServiceImpl(MeliRepository meliRepository, Map<Integer, Integer> requestMap) {
        this.meliRepository = meliRepository;
        this.requestMap = new HashMap<>();
    }

    @Override
    public List<ProductDTO> getProducts(ParamsDTO params) throws BadRequestExceedsNumberOfFilters, BadRequestTypeOrderInvalid {
        return meliRepository.getProducts(params);
    }

    @Override
    public ResponsePurchaseDTO processPurchaseRequest(PurchaseDTO request) throws BadRequestExceedsNumberOfFilters, BadRequestTypeOrderInvalid, ProductIdNotFound, ProductNoStock {
        List<ProductDTO> productsInStock;
        this.requestMap.clear();
        this.addArticles(request.getArticles());
        ResponsePurchaseDTO response = null;
        TicketDTO ticket = null;
        StatusDTO status = null;

        productsInStock = meliRepository.getProducts(new ParamsDTO());
        double totalAmount = this.calculatePurchase(request, productsInStock);
        ticket = new TicketDTO(meliRepository.getTicketCount(), totalAmount, getListOfProducts(productsInStock));
        response = new ResponsePurchaseDTO();
        response.setTicket(ticket);
        status = new StatusDTO(200, "La solicitud de compra se completo con exito");
        response.setStatusCode(status);

        meliRepository.saveTicket(ticket);

        return response;
    }

    @Override
    public ShippingCartDTO finishBuy() {
        return meliRepository.getPurchases();
    }

    private double calculatePurchase(PurchaseDTO request, List<ProductDTO> products) throws ProductNoStock, ProductIdNotFound {
        double total = 0.0;
        boolean productExists = false;
        for (Integer id : this.requestMap.keySet()) {
            for (ProductDTO prd : products) {
                if (prd.getProductId().equals(id)) {
                    if (this.requestMap.get(id) <= prd.getQuantity()) {
                        total = total + this.requestMap.get(id) * prd.getPrice();
                        meliRepository.updateProductQuantity(prd.getProductId(),this.requestMap.get(id));
                    } else {
                        throw new ProductNoStock(prd.getName(),this.requestMap.get(id),prd.getQuantity());
                    }
                    productExists = true;
                }
            }
            if (productExists)
                productExists = false;
            else
                throw new ProductIdNotFound();
        }
        return total;
    }

    private List<ArticleDTO> getListOfProducts(List<ProductDTO> products) {
        List<ArticleDTO> articles = new ArrayList<>();
        for (Integer id : this.requestMap.keySet()) {
            for (ProductDTO prd : products) {
                if (prd.getProductId().equals(id)) {
                    ArticleDTO art = new ArticleDTO(prd.getProductId(),
                            prd.getName(),
                            prd.getBrand(),
                            this.requestMap.get(id));
                    articles.add(art);
                }
            }
        }
        return articles;

    }

    private void addArticles(List<ArticleDTO> articles) {
        for (ArticleDTO art : articles) {
            this.requestMap.put(art.getProductId(), art.getQuantity());
        }
    }


}
