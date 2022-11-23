package com.flights.DAO;

import org.jetbrains.annotations.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public final class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private Integer passport;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static @NotNull Builder newBuilder() {
        return new Client().new Builder();
    }

    public class Builder{
        private Builder(){}

        public Builder setName(String name) {
            Client.this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            Client.this.email = email;
            return this;
        }

        public Builder setPassport(Integer passport) {
            Client.this.passport = passport;
            return this;
        }

        public Client build() {
            return Client.this;
        }
    }
}
