package com.example.testaufgabe_reservation.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Restaurant {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private List<Table> tables;

    public Restaurant(String name, List<Table> tables) {
        this.name = name;
        this.tables = tables;
    }

}