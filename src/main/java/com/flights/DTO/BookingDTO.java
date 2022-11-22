package com.flights.DTO;

import java.sql.Date;

public class BookingDTO {
    private final String clientName;
    private final String startCity;
    private final String endCity;
    private final Date dateStart;
    private final String timeFlying;

    public BookingDTO(String clientName, String startCity, String endCity, Date dateStart, String timeFlying) {
        this.clientName = clientName;
        this.startCity = startCity;
        this.endCity = endCity;
        this.dateStart = dateStart;
        this.timeFlying = timeFlying;
    }
}
