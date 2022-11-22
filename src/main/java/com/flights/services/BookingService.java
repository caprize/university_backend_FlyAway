package com.flights.services;

import com.flights.DAO.Booking;
import com.flights.DAO.Client;
import com.flights.DAO.Flight;
import com.flights.DTO.BookingDTO;
import com.flights.Main;
import com.flights.repos.BookingRepository;
import com.flights.repos.ClientRepository;
import com.flights.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final FlightRepository flightRepository;


    @Autowired
    public BookingService(BookingRepository bookingRepository, ClientRepository clientRepository, FlightRepository flightRepository) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.flightRepository = flightRepository;
    }

    /* TODO
    Добавить ограничение на места
     */

    public String registration(Integer passport, Long id_flight) {
        Optional<Client> clientOptional = clientRepository.findByPassport(passport);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            Booking booking = new Booking();
            booking.setClientId(client.getId());
            booking.setFlightId(id_flight);
            booking.setStatus("Вылет вовремя");

            Optional<Booking> bookingOptional = bookingRepository.findBookingByClientIdAndFlightId(client.getId(), id_flight);
            if (bookingOptional.isEmpty()) {
                try {
                    bookingRepository.save(booking);

                    return Main.GSON.toJson("Booking is created. " + client.getName() +
                            ", you have to pay your booking in order section");

                } catch (Exception e) {
                    return Main.GSON.toJson(e.getMessage());
                }
            }

            return "You paid for the order.";

        } else {
            return Main.GSON.toJson("Пользователь не зарегестрирован.");
        }
    }

    public String get(Integer passport) {
        Optional<Client> clientOptional = clientRepository.findByPassport(passport);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            List<Booking> bookingList = bookingRepository.findByClientId(client.getId());
            List<BookingDTO> result = new ArrayList<>();

            for (Booking booking : bookingList) {
                Optional<Flight> flightOptional = flightRepository.findById(booking.getFlightId());

                if (flightOptional.isPresent()) {
                    Flight flight = flightOptional.get();

                    result.add(new BookingDTO(client.getName(), flight.getStartCity(), flight.getEndCity(),
                            flight.getDateStart(), flight.getTimeFlying()));

                }
            }
            return Main.GSON.toJson(result);
        } else {
            return Main.GSON.toJson("Заказов нет.");
        }
    }

    public String melt(Long idClient, Long idFlight) {
        Optional<Booking> booking = bookingRepository.findBookingByClientIdAndFlightId(idClient, idFlight);

        if (booking.isPresent()) {
            return Main.GSON.toJson("You paid for the order.");
        } else {
            return Main.GSON.toJson("Данного заказа не существует.");
        }
    }

//    public String mailing(String status) {
//
//    }

}
