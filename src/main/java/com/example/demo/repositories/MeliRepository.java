package com.example.demo.repositories;

import com.example.demo.dto.ParamsDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.exceptions.BadRequestExceedsNumberOfFilters;

import java.util.List;

public interface MeliRepository {
    List<ProductDTO> getProducts(ParamsDTO params) throws BadRequestExceedsNumberOfFilters;
}
