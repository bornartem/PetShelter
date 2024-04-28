-- liquibase formatted sql
-- changeset bkvdm:1
CREATE TABLE daily_report (
  id_daily_report BIGSERIAL PRIMARY KEY,
  id_client BIGINT,
  date_time TIMESTAMP NOT NULL,
  well VARCHAR(255),
  reaction VARCHAR(255),
  animal_menu VARCHAR(255),
  foto_animal BYTEA[],
  photo_path VARCHAR(255),
  is_check BOOLEAN NOT NULL,
  FOREIGN KEY (id_client) REFERENCES Clients(id_client)
);

CREATE TABLE animals (
  id_animal BIGINT PRIMARY KEY,
  id_shelter BIGINT,
  name VARCHAR(255),
  age VARCHAR(255),
  type VARCHAR(255),
  busy_free BOOLEAN NOT NULL,
  date_take TIMESTAMP,
  FOREIGN KEY (id_shelter) REFERENCES shelter(id_shelter)
);

CREATE TABLE clients (
  id_client BIGINT PRIMARY KEY,
  id_volunteer BIGINT,
  chat_id BIGINT NOT NULL,
  name VARCHAR(255),
  contact VARCHAR(255),
  adopted_animal BIGINT,
  FOREIGN KEY (id_volunteer) REFERENCES volunteers(id_volunteer),
  FOREIGN KEY (adopted_animal) REFERENCES Animals(id_animal)
);

CREATE TABLE shelter (
  id_shelter BIGINT PRIMARY KEY,
  name VARCHAR(255),
  working_hours VARCHAR(255),
  contact VARCHAR(255),
  address VARCHAR(255),
  location VARCHAR(255),
  security_contact VARCHAR(255),
  rules VARCHAR(255)
);

CREATE TABLE volunteers (
  id_volunteer BIGINT PRIMARY KEY,
  chat_id BIGINT NOT NULL,
  name VARCHAR(255),
  contact VARCHAR(255)
);

CREATE TABLE animal_avatar (
  id BIGINT PRIMARY KEY,
  file_path VARCHAR(255),
  file_size BIGINT,
  media_type VARCHAR(255),
  data BYTEA,
  animal_id BIGINT,
  FOREIGN KEY (animal_id) REFERENCES animals(id_animal)
);

CREATE TABLE conversation_people (
  id BIGINT PRIMARY KEY,
  chat_id BIGINT NOT NULL,
  opponent_chat_id BIGINT NOT NULL,
  is_volunteer BOOLEAN NOT NULL
);

CREATE TABLE notification_task (
id BIGSERIAL PRIMARY KEY,
  chat_id BIGINT NOT NULL,
  date_time TIMESTAMP NOT NULL,
  notification VARCHAR(255) NOT NULL
);

select * from animals