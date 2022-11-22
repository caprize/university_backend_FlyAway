package com.flights.DAO;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public final class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_client;
}
