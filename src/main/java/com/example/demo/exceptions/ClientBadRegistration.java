package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public class ClientBadRegistration extends MeliException{
    public ClientBadRegistration(){
        ErrorDTO error = new ErrorDTO();
        error.setName("Bad Request for registration");
        error.setDescription("Invalid data");
        this.setError(error);
        this.setStatus(HttpStatus.BAD_REQUEST);
    }
}
