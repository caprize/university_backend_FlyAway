package com.flights.repos;

import com.flights.DAO.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    List<Booking> findByClientId(Long clientId);
    Optional<Booking> findBookingByClientIdAndFlightId(Long clientId, Long flightId);
}
