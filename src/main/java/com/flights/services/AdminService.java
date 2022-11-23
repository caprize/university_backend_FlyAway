package com.flights.services;

import com.flights.DAO.Admin;
import com.flights.DAO.Client;
import com.flights.Main;
import com.flights.repos.AdminRepository;
import com.flights.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, ClientRepository clientRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
    }

    public String get(Integer id) {
        Optional<Admin> adminOptional = adminRepository.findById(id);

        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();

            return Main.GSON.toJson(admin);
        } else {
            return Main.GSON.toJson("Такого админа не существует.");
        }
    }

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
}
