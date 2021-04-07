package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public class BadRequestTypeOrderInvalid extends MeliException{
    public BadRequestTypeOrderInvalid(){
        ErrorDTO error = new ErrorDTO();
        error.setName("Invalid Order Type");
        error.setDescription("No existe ese tipo de ordenamiento");
        this.setError(error);
        this.setStatus(HttpStatus.BAD_REQUEST);
    }
}
