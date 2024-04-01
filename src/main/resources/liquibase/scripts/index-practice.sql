-- liquibase formatted sql
-- changeset bornartem:1
CREATE TABLE volunteer(
    id bigserial not null,
    name varchar(255) not null,
    contact varchar not null,
    primary key(id)
);
-- changeset bornartem:2
CREATE TABLE visitor(
    id bigserial not null,
    name varchar not null,
    contact varchar not null,
    primary key(id)
);