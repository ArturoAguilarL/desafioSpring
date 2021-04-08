package com.example.demo.controllers;


import com.example.demo.dto.ClientDTO;
import com.example.demo.exceptions.ClientAlreadyExist;
import com.example.demo.exceptions.ClientBadRegistration;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping(value = "api/v1/newClient")
    public void newClient(@RequestBody ClientDTO client) throws ClientBadRegistration, ClientAlreadyExist {
        clientService.newClient(client);
    }

    @GetMapping(value = "api/v1/getClients")
    public List<ClientDTO> getClients(@RequestParam(value = "province", required = false) String province){
        return clientService.getClients(province);
    }
}
