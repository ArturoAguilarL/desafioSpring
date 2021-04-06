package com.example.demo.controllers;


import com.example.demo.dto.ProductDTO;
import com.example.demo.services.MeliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MeliController {

    @Autowired
    MeliService meliService;

    @GetMapping(value = "api/v1/articles")
    public List<ProductDTO> getProducts() {
        return meliService.getProducts();
    }
}
