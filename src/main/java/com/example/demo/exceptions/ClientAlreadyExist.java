package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public class ClientAlreadyExist extends MeliException{
    public ClientAlreadyExist(){
        ErrorDTO error = new ErrorDTO();
        error.setName("Bad Request for registration");
        error.setDescription("ClientAlready exist");
        this.setError(error);
        this.setStatus(HttpStatus.BAD_REQUEST);
    }
}
