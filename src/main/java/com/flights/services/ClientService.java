package com.flights.services;

import com.flights.Main;
import com.flights.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public String all() {
        return Main.GSON.toJson(clientRepository.findAll());
    }
}
