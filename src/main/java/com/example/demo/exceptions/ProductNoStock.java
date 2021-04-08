package com.example.demo.exceptions;

import com.example.demo.dto.ErrorDTO;
import org.springframework.http.HttpStatus;

public class ProductNoStock extends MeliException{

    public ProductNoStock(String productName,Integer quantity,Integer realQuantity){
        ErrorDTO error = new ErrorDTO();
        error.setName("No Stock product:"+productName);
        error.setDescription("Cantidad del producto pedida:  "+quantity +"   no existe en stock, en stock solo hay:  "+realQuantity);
        this.setError(error);
        this.setStatus(HttpStatus.BAD_REQUEST);
    }
}
