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

    public String registration(String name, String email, String login, String password, Integer passport) {
        Client client = Client.newBuilder()
                .setName(name)
                .setEmail(email)
                .setLogin(login)
                .setPassport(passport)
                .setPassword(password)
                .build();

        try {
            clientRepository.save(client);

            return Main.GSON.toJson("200");
        } catch (Exception e) {
            return Main.GSON.toJson(e.getMessage());
        }
    }
}
