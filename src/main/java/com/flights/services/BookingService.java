package com.flights.services;

import com.flights.DAO.Booking;
import com.flights.DAO.Client;
import com.flights.Main;
import com.flights.repos.BookingRepository;
import com.flights.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;


    @Autowired
    public BookingService(BookingRepository bookingRepository, ClientRepository clientRepository) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
    }

    public String registration(Integer passport, Long id_flight) {
        Optional<Client> clientOptional = clientRepository.findByPassport(passport);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();

            Booking booking = new Booking();
            booking.setClientId(client.getId());
            booking.setFlightId(id_flight);
            booking.setStatus("Вылет вовремя");

            try {
                bookingRepository.save(booking);

                return Main.GSON.toJson("Заказ создан. " + client.getName() +
                        ", вам нужно оплатить заказ в разделе 'Заказы'");
            } catch (Exception e) {
                return Main.GSON.toJson(e.getMessage());
            }
        } else {
            return Main.GSON.toJson("Пользователь не зарегестрирован.");
        }
    }

    public String get(Integer passport) {
        Optional<Client> clientOptional = clientRepository.findByPassport(passport);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            List<Booking> bookingList = bookingRepository.findByClientId(client.getId());

            return Main.GSON.toJson(bookingList);
        } else {
            return Main.GSON.toJson("Заказов нет.");
        }
    }

    public String melt(Long idClient, Long idFlight) {
        Optional<Booking> booking = bookingRepository.findBookingByClientIdAndFlightId(idClient, idFlight);

        if (booking.isPresent()) {
            return Main.GSON.toJson("Заказ оплачен.");
        } else {
            return Main.GSON.toJson("Данного заказа не существует.");
        }
    }

    public String mailing(String status) {

    }

}
