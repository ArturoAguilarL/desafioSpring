package com.example.demo.services;

import com.example.demo.dto.ProductDTO;
import com.example.demo.repositories.MeliRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeliServiceImpl implements MeliService {
    @Autowired
    private final MeliRepository meliRepository;

    public MeliServiceImpl(MeliRepository meliRepository) {
        this.meliRepository = meliRepository;
    }


    @Override
    public List<ProductDTO> getProducts() {
        return meliRepository.getProducts();
    }
}
