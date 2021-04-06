package com.example.demo.services;

import com.example.demo.dto.ParamsDTO;
import com.example.demo.dto.ProductDTO;

import java.util.List;

public interface MeliService {

    public List<ProductDTO> getProducts(ParamsDTO params);
}
