package com.flights.controllers;

import com.flights.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/registration")
    public @ResponseBody String registration(@RequestParam String name, @RequestParam String email,
                                             @RequestParam Integer passport) {
        return clientService.registration(name, email, passport);
    }

    @GetMapping
    public @ResponseBody String all() {
        return clientService.all();
    }
}
