package com.example.demo.services;

import com.example.demo.dto.ParamsDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.PurchaseDTO;
import com.example.demo.dto.ResponsePurchaseDTO;
import com.example.demo.exceptions.BadRequestExceedsNumberOfFilters;
import com.example.demo.exceptions.BadRequestTypeOrderInvalid;
import com.example.demo.exceptions.ProductIdNotFound;
import com.example.demo.exceptions.ProductNoStock;

import java.util.List;

public interface MeliService {

    public List<ProductDTO> getProducts(ParamsDTO params) throws BadRequestExceedsNumberOfFilters, BadRequestTypeOrderInvalid;

    public ResponsePurchaseDTO processPurchaseRequest(PurchaseDTO request) throws BadRequestExceedsNumberOfFilters, BadRequestTypeOrderInvalid, ProductIdNotFound, ProductNoStock;
}
