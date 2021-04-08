package com.example.demo.repositories;

import com.example.demo.dto.*;
import com.example.demo.exceptions.BadRequestExceedsNumberOfFilters;
import com.example.demo.exceptions.BadRequestTypeOrderInvalid;

import java.util.List;

public interface MeliRepository {
    List<ProductDTO> getProducts(ParamsDTO params) throws BadRequestExceedsNumberOfFilters, BadRequestTypeOrderInvalid;

    public int getTicketCount();

    void updateProductQuantity(Integer productId, Integer integer);

    void saveTicket(TicketDTO ticket);

    ShippingCartDTO getPurchases();
}
