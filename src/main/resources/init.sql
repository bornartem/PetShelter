--liquibase formatted sql
--changeset bkvdm:1
CREATE TABLE daily_report (
    idDailyReport bigint PRIMARY KEY AUTO_INCREMENT,
    id_animal bigint NOT NULL,
    date_time DATETIME NOT NULL,
    well VARCHAR(255),
    reaction VARCHAR(255),
    foto_animal BLOB,
    check BOOL NOT NULL,
    FOREIGN KEY (id_animal) REFERENCES Animals(id)
);