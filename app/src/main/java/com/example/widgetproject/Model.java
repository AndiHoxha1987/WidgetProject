package com.example.widgetproject;

public class Model {
    private String name;
    private final String color;
    private final int amount;


    public Model(String name, String color, int amount) {
        this.name = name;
        this.color = color;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }


    public String getColor() {
        return color;
    }

    public int getAmount() {
        return amount;
    }
}
