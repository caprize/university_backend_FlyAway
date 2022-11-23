package com.flights.controllers;

import com.flights.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping()
    public @ResponseBody String get(@RequestParam Integer id) {
        return adminService.get(id);
    }

    @PostMapping()
    public @ResponseBody String registration(@RequestParam String name, @RequestParam String email,
                                             @RequestParam Integer passport) {
        return adminService.registration(name, email, passport);
    }
}
