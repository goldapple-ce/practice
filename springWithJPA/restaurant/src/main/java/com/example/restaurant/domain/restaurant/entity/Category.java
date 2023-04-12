package com.example.restaurant.domain.restaurant.entity;

public enum Category {
    KOREA("한식"),
    CHINA("중식"),
    JAPAN("일식");

    private final String description;

    Category(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
