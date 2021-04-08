package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class ShippingCartDTO {
    List<TicketDTO> tickets;
    Double total;
    StatusDTO statusCode;

    public ShippingCartDTO(){
        this.tickets = new ArrayList<>();
    }

    public void addTickets(List<TicketDTO> auxCopy) {
        for(TicketDTO t : auxCopy){
            tickets.add(t);
        }
    }
}
