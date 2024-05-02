package com.example.petShelter.exception;

public class AlreadyExistInDB extends RuntimeException {
    public AlreadyExistInDB(String text) {
        super(text);
    }
}
