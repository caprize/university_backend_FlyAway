package com.flights.repos;

import com.flights.DAO.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, Long>{
    List<Flight> findAllByEndCityAndStartCityAndDateStart(String endCity, String startCity, Date dateStart);
}
