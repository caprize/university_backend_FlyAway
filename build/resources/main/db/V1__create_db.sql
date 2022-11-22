CREATE TABLE flight (
    id           SERIAL          NOT NULL,
    count        INT             NOT NULL,
    date_start   DATE            NOT NULL,
    time_start   VARCHAR(255)    NOT NULL,
    time_flying  VARCHAR(255)    NOT NULL,
    cost         INT             NOT NULL,
    start_city   VARCHAR(255)    NOT NULL,
    end_city     VARCHAR(255)    NOT NULL,
    CONSTRAINT flight_pk PRIMARY KEY (id)
);

CREATE TABLE client (
    id          SERIAL       NOT NULL,
    name        VARCHAR(255) NOT NULL,
    email       VARCHAR(255) DEFAULT NULL,
    passport    INT          NOT NULL,
    CONSTRAINT client_pk PRIMARY KEY (id)
);

CREATE TABLE booking (
    id          SERIAL       NOT NULL,
    client_id   BIGINT       NOT NULL REFERENCES client (id) ON UPDATE CASCADE ON DELETE CASCADE,
    flight_id   BIGINT       NOT NULL REFERENCES flight (id) ON UPDATE CASCADE ON DELETE CASCADE,
    status      VARCHAR(255) DEFAULT NULL,
    CONSTRAINT booking_pk PRIMARY KEY (id)
);

CREATE TABLE admin (
    id_client INT NOT NULL REFERENCES client (id) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT admin_pk PRIMARY KEY (id_client)
);