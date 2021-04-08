package com.example.demo.repositories;

import com.example.demo.dto.ClientDTO;
import com.example.demo.exceptions.ClientAlreadyExist;

import java.util.List;

public interface ClientRepository {
    void saveClient(ClientDTO client) throws ClientAlreadyExist;

    List<ClientDTO> getClients(String param);
}
