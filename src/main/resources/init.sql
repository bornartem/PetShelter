--liquibase formatted sql
--changeset bkvdm:1
CREATE TABLE DailyReport
(
    idDailyReport INT PRIMARY KEY AUTO_INCREMENT,
    id_valunteer  INT,
    id_animal     INT,
    dateTime      DATETIME NOT NULL,
    well          VARCHAR(255),
    reaction      VARCHAR(255),
    foto_animal   LONGBLOB,
    FOREIGN KEY (id_valunteer) REFERENCES Valunteers (idValunteer),
    FOREIGN KEY (id_animal) REFERENCES Animals (idAnimal)
);