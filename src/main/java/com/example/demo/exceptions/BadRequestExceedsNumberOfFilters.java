package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public class BadRequestExceedsNumberOfFilters extends MeliException{
    public BadRequestExceedsNumberOfFilters(){
        ErrorDTO error = new ErrorDTO();
        error.setName("Bad Request");
        error.setDescription("Cantidad de filtros supera el maximo. Maximo de filtros = 2. Quantity no se puede con combinacion");
        this.setError(error);
        this.setStatus(HttpStatus.BAD_REQUEST);
    }

}
