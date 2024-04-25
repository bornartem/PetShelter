package com.example.petShelter.model;

public enum DailyReportsEnum {


    HEALTH("health"),
    REACTION("reaction"),
    ANIMAL_MENU("animalMenu"),
    PHOTO("photo");
    private String fieldName;

    DailyReportsEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return fieldName;
    }
}
