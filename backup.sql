CREATE DATABASE IF NOT EXISTS poker;

use poker;

DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS event;

CREATE TABLE IF NOT EXISTS event
(
    eventID int NOT NULL AUTO_INCREMENT,
    eventName varchar(50) NOT NULL,
    CONSTRAINT event_pk PRIMARY KEY (eventID)
);

CREATE TABLE IF NOT EXISTS player
(
    playerID int NOT NULL AUTO_INCREMENT,
    playerType char(7) NOT NULL,
    firstName varchar(30) NOT NULL,
    lastName varchar(30) NOT NULL,
    age smallint(3) NOT NULL,
    phone char(10) NOT NULL,
    gender char(6) NOT NULL,
    streetNum smallint(4) NOT NULL,
    streetName varchar(30) NOT NULL,
    suburb varchar(30) NOT NULL,
    postcode int(5) NOT NULL,
	earnings int(20) ,
    buyin int(6) NOT NULL,
    eventID	int NOT NULL,
    CONSTRAINT player_pk PRIMARY KEY (playerID),
    CONSTRAINT player_event_fk FOREIGN KEY (eventID) REFERENCES event (eventID)
);
