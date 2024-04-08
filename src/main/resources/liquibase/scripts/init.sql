-- liquibase formatted sql
-- changeset bkvdm:1
CREATE TABLE daily_report (
  id_daily_report BIGINT PRIMARY KEY,
  id_animal BIGINT,
  date_time TIMESTAMP NOT NULL,
  well VARCHAR(255),
  reaction VARCHAR(255),
  foto_animal BYTEA,
  is_check BOOLEAN NOT NULL,
  FOREIGN KEY (id_animal) REFERENCES Animals(id_animal)
);

CREATE TABLE animals (
  id_animal BIGINT PRIMARY KEY,
  id_shelter BIGINT,
  name VARCHAR(255),
  type VARCHAR(255),
  busy_free BOOLEAN NOT NULL,
  date_take TIMESTAMP,
  FOREIGN KEY (id_shelter) REFERENCES Shelters(id_shelter)
);
CREATE TABLE clients (
  id_client BIGINT PRIMARY KEY,
  id_volunteer BIGINT,
  chat_id BIGINT NOT NULL,
  name VARCHAR(255),
  contact VARCHAR(255),
  FOREIGN KEY (id_volunteer) REFERENCES Volunteers(id_volunteer)
);
CREATE TABLE shelter (
  id_shelter BIGINT PRIMARY KEY,
  name VARCHAR(255),
  working_hours VARCHAR(255),
  contact VARCHAR(255),
  address VARCHAR(255),
  location VARCHAR(255),
  security_contact VARCHAR(255)
);
CREATE TABLE volunteers (
  id_volunteer BIGINT PRIMARY KEY,
  chat_id BIGINT NOT NULL,
  name VARCHAR(255),
  contact VARCHAR(255)
);