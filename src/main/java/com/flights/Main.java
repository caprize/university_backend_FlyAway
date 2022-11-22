package com.flights;

import com.flights.initDB.DBInfo;
import com.flights.initDB.InitializationDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {
    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting().create();

    public static void main(String[] args) {
        DBInfo CREDS = new DBInfo();

        InitializationDB initializer = new InitializationDB(CREDS);
        initializer.initDB();

        SpringApplication.run(Main.class, args);
    }
}
