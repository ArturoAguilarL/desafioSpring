package com.example.demo.controllers;


import com.example.demo.dto.ErrorDTO;
import com.example.demo.dto.ParamsDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.exceptions.BadRequestExceedsNumberOfFilters;
import com.example.demo.exceptions.BadRequestTypeOrderInvalid;
import com.example.demo.exceptions.MeliException;
import com.example.demo.services.MeliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ExceptionHandler(MeliException.class)
    public ResponseEntity<ErrorDTO> handleException(MeliException exception){
        return new ResponseEntity<>(exception.getError(),exception.getStatus());
    }
}
