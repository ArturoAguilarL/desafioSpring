package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public class ProductIdNotFound extends MeliException{
    public ProductIdNotFound(){
        ErrorDTO error = new ErrorDTO();
        error.setName("Invalid Product Id");
        error.setDescription("No existe ese producto");
        this.setError(error);
        this.setStatus(HttpStatus.BAD_REQUEST);
    }
}
