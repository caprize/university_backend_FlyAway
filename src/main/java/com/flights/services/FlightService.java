package com.flights.services;

import com.flights.DAO.Admin;
import com.flights.DAO.Flight;
import com.flights.Main;
import com.flights.repos.AdminRepository;
import com.flights.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, AdminRepository adminRepository) {
        this.flightRepository = flightRepository;
        this.adminRepository = adminRepository;
    }

    public String add(Integer id, Integer count, Date start, String timeStart, Integer cost, String timeFlying,
                      String from, String where) {
        Optional<Admin> adminOptional = adminRepository.findById(id);

        if (adminOptional.isPresent()) {
            Flight flight = new Flight();
            flight.setCost(cost);
            flight.setCount(count);
            flight.setDateStart(start);
            flight.setTimeStart(timeStart);
            flight.setTimeFlying(timeFlying);
            flight.setStartCity(from);
            flight.setEndCity(where);

            try {
                flightRepository.save(flight);

                return Main.GSON.toJson("200");
            } catch (Exception e) {
                return Main.GSON.toJson(e.getMessage());
            }

        } else {
            return Main.GSON.toJson("Вы не можете добавлять рейсы.");
        }
    }

    public String get(String endCity, Date start, String timeStart) {
        return Main.GSON.toJson(flightRepository.findAllByEndCityAndDateStartAndTimeStart(endCity, start, timeStart));
    }

    public String all() {
        return Main.GSON.toJson(flightRepository.findAll());
    }
}
