package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
@Setter
public class ResponsePurchaseDTO {
    TicketDTO ticket;
    StatusDTO statusCode;
    //HttpStatus status;

    public ResponsePurchaseDTO() {

    }
}
