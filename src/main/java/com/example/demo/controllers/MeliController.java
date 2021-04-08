package com.example.demo.controllers;


import com.example.demo.dto.*;
import com.example.demo.exceptions.*;
import com.example.demo.services.MeliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MeliController {

    @Autowired
    MeliService meliService;

    @GetMapping(value = "api/v1/articles")
    public List<ProductDTO> getProducts(@RequestParam(value = "name", required = false) String name,
                                        @RequestParam(value = "category", required = false) String category,
                                        @RequestParam(value = "brand", required = false) String brand,
                                        @RequestParam(value = "price", required = false) Double price,
                                        @RequestParam(value = "quantity", required = false) Integer quantity,
                                        @RequestParam(value = "freeShipping", required = false) Boolean freeShipping,
                                        @RequestParam(value = "prestige", required = false) Integer prestige,
                                        @RequestParam(value = "order", required = false) Integer order) throws BadRequestExceedsNumberOfFilters, BadRequestTypeOrderInvalid {
        ParamsDTO params = new ParamsDTO(name,category,brand,price,quantity,freeShipping,prestige,order);
        //Le paso el objeto params y si alguno esta seteado, se extrae lo seteado desde el objeto params
        //Los que no estan seteados, seran null y no se van a tener en cuenta
        return meliService.getProducts(params);
    }

    @PostMapping(value = "api/v1/purchase-request")
    public ResponsePurchaseDTO purchaseRequest(@RequestBody PurchaseDTO request) throws ProductIdNotFound, BadRequestExceedsNumberOfFilters, ProductNoStock, BadRequestTypeOrderInvalid {
        return meliService.processPurchaseRequest(request);
    }

    @GetMapping(value = "api/v1/purchase-request/finishBuy")
    public ShippingCartDTO finishBuy() throws ProductIdNotFound, BadRequestExceedsNumberOfFilters, ProductNoStock, BadRequestTypeOrderInvalid {
        return meliService.finishBuy();
    }

    @ExceptionHandler(MeliException.class)
    public ResponseEntity<ErrorDTO> handleException(MeliException exception){
        return new ResponseEntity<>(exception.getError(),exception.getStatus());
    }
}
