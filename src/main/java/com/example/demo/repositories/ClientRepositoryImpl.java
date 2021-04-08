package com.example.demo.repositories;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.exceptions.ClientAlreadyExist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {
    List<ClientDTO> clients;
    Integer cont;

    public ClientRepositoryImpl() {
        this.clients = new ArrayList<>();
        cont = 0;
    }

    @Override
    public void saveClient(ClientDTO client) throws ClientAlreadyExist {
        for (ClientDTO cli : clients) {
            if(cli.getEmail().equals(client.getEmail())) {
                throw new ClientAlreadyExist();
            }
        }
        client.setClientId(cont);
        this.clients.add(client);
        cont++;

    }

        @Override
        public List<ClientDTO> getClients (String param){
            List<ClientDTO> clientsCopy = new ArrayList<ClientDTO>(this.clients);
            //Hacer el filtrado
            if (param != null) {
                clientsCopy.removeIf(p -> !p.getAddress().getProvince().equals(param));
            }
            return clientsCopy;
        }
    }
