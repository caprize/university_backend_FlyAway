package com.flights.services;

import com.flights.DAO.Admin;
import com.flights.Main;
import com.flights.repos.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
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
}
