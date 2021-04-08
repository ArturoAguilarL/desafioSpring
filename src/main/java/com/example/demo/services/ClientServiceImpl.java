package com.example.demo.services;

import com.example.demo.dto.ClientDTO;
import com.example.demo.exceptions.ClientAlreadyExist;
import com.example.demo.exceptions.ClientBadRegistration;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    ClientRepository clientRepository;


    @Override
    public void newClient(ClientDTO client) throws ClientBadRegistration, ClientAlreadyExist {
        if(this.validateClient(client))
            clientRepository.saveClient(client);
        else
            throw new ClientBadRegistration();
    }

    @Override
    public List<ClientDTO> getClients(String param) {
        return clientRepository.getClients(param);
    }


    private boolean validateClient(ClientDTO client){
        //Validar client con regex
        return true;
    }
}
