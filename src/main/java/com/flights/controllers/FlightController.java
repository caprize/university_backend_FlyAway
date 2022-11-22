package com.flights.controllers;

import com.flights.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public @ResponseBody String add(@RequestParam Integer id_admin, @RequestParam Integer count,
                                    @RequestParam Date date_start, @RequestParam String timeStart,
                                    @RequestParam Integer cost, @RequestParam String timeFlying,
                                    @RequestParam String from, @RequestParam String where) {
        return flightService.add(id_admin, count, date_start, timeStart, cost, timeFlying, from, where);
    }

    @GetMapping("/get")
    public @ResponseBody String get(@RequestParam String endCity, @RequestParam String startCity, @RequestParam Date dateStart,
                                    @RequestParam String timeStart) {
        return flightService.get(endCity, startCity, dateStart, timeStart);
    }

    @GetMapping("/all")
    public @ResponseBody String all() {
        return flightService.all();
    }
}
