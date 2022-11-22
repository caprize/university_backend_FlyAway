package com.flights.services;

import com.flights.DAO.Client;
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


    /* TODO
    * Перенести функционал в админку */
    public String registration(String name, String email, Integer passport) {
        Client client = Client.newBuilder()
                .setName(name)
                .setEmail(email)
                .setPassport(passport)
                .build();

        try {
            clientRepository.save(client);

            return Main.GSON.toJson("200");
        } catch (Exception e) {
            return Main.GSON.toJson(e.getMessage());
        }
    }

    public String all() {
        return Main.GSON.toJson(clientRepository.findAll());
    }
}
