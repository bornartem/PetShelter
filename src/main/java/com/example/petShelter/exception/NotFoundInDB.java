package com.example.petShelter.exception;

public class NotFoundInDB extends RuntimeException{
    public NotFoundInDB(String text){
        super(text);
    }
}
