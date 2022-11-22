package com.flights.controllers;

import com.flights.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
                                             @RequestParam String login, @RequestParam String password,
                                             @RequestParam Integer passport) {
        return clientService.registration(name, email, login, password, passport);
    }
}
