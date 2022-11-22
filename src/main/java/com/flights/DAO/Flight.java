package com.flights.DAO;

import lombok.Getter;

import javax.persistence.*;
import java.sql.Date;

@Entity
public final class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer count;
    private Date dateStart;
    private String timeStart;
    private String timeFlying;
    private Integer cost;
    private String startCity;
    private String endCity;

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setDateStart(Date date_start) {
        this.dateStart = date_start;
    }

    public void setTimeStart(String time_start) {
        this.timeStart = time_start;
    }

    public void setTimeFlying(String time_flying) {
        this.timeFlying = time_flying;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setStartCity(String from) {
        this.startCity = from;
    }

    public void setEndCity(String where) {
        this.endCity = where;
    }

    public Long getId() {
        return id;
    }

    public Integer getCount() {
        return count;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public String getTimeFlying() {
        return timeFlying;
    }

    public Integer getCost() {
        return cost;
    }

    public String getStartCity() {
        return startCity;
    }

    public String getEndCity() {
        return endCity;
    }
}
