package com.example.demo.services;

import com.example.demo.dto.ClientDTO;
import com.example.demo.exceptions.ClientAlreadyExist;
import com.example.demo.exceptions.ClientBadRegistration;

import java.util.List;

public interface ClientService {

    void newClient(ClientDTO client) throws ClientBadRegistration, ClientAlreadyExist;

    List<ClientDTO> getClients(String param);
}
