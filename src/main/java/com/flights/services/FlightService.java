package com.flights.services;

import com.flights.DAO.Admin;
import com.flights.DAO.Booking;
import com.flights.DAO.Client;
import com.flights.DAO.Flight;
import com.flights.Main;
import com.flights.repos.AdminRepository;
import com.flights.repos.BookingRepository;
import com.flights.repos.ClientRepository;
import com.flights.repos.FlightRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AdminRepository adminRepository;
    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private static final String NOREPLY_ADDRESS = "noreply@baeldung.com";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    public FlightService(FlightRepository flightRepository, AdminRepository adminRepository, BookingRepository bookingRepository, ClientRepository clientRepository) {
        this.flightRepository = flightRepository;
        this.adminRepository = adminRepository;
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
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
            flight.setStatus("Departure on time");

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

    public String getTime(String endCity, String startCity, Date start, @NotNull String timeStart) {
        List<Flight> flights = flightRepository.findAllByEndCityAndStartCityAndDateStart(endCity, startCity, start);
        List<Flight> result = new ArrayList<>();
        Integer hours = Integer.parseInt(timeStart.split(":")[0]);
        int minutes = Integer.parseInt(timeStart.split(":")[1]);

        for (Flight flight : flights) {
            Integer hoursFlight = Integer.parseInt(timeStart.split(":")[0]);
            int minutesFlight = Integer.parseInt(timeStart.split(":")[1]);

            if ((hoursFlight > hours) || (hoursFlight.equals(hours) && minutesFlight > minutes)) {
                result.add(flight);
            }
        }

        return Main.GSON.toJson(result);
    }

    public String get(String endCity, String startCity, Date start) {
        return Main.GSON.toJson(flightRepository.findAllByEndCityAndStartCityAndDateStart(endCity, startCity, start));
    }

    public String all() {
        return Main.GSON.toJson(flightRepository.findAll());
    }

    public String changeStatus(@NotNull String timeStart, Long idFlight, Date date) {
        Optional<Flight> flightOptional = flightRepository.findById(idFlight);

        if (flightOptional.isPresent()) {
            Flight flight = flightOptional.get();
            flight.setDateStart(date);
            flight.setTimeStart(timeStart);

            flight.setStatus("Flight was rescheduled for " + date + " " + timeStart);
            flightRepository.save(flight);
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(NOREPLY_ADDRESS);
            message.setSubject("Flight time has changed.");
            message.setText("Flight was rescheduled for " + date + " " + timeStart + ". Flight number " + idFlight);

            List<Booking> bookingList = bookingRepository.findAllByFlightId(idFlight);

            for (Booking booking : bookingList) {
                Optional<Client> clientOptional = clientRepository.findById(booking.getClientId());

                if (clientOptional.isPresent()) {
                    Client client = clientOptional.get();

                    message.setTo(client.getEmail());
                    mailSender.send(message);
                }
            }
        } catch (MailException e) {
            return Main.GSON.toJson(e.getMessage());
        }
        return Main.GSON.toJson("Clients notified.");
    }
}
