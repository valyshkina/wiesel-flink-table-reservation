package com.example.testaufgabe_reservation.model;

import lombok.Getter;
import lombok.Setter;

public class Table implements Comparable<Table> {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private int capacity;
    @Getter @Setter
    private String reservedBy;
    @Getter @Setter
    private boolean isReserved;
    @Getter @Setter
    private int reservationNumber;

    public Table(int id, int capacity, String reservedBy) {
        this.id = id;
        this.capacity = capacity;
        this.reservedBy = reservedBy;
        this.isReserved = false;
    };

    public Table(int id, int capacity, String reservedBy, int reservationNumber ) {
        this.id = id;
        this.capacity = capacity;
        this.reservedBy = reservedBy;
        this.isReserved = false;
        this.reservationNumber = reservationNumber;
    };

  public boolean isReserved() {
        return isReserved;
    }

    public void reserve() {
        this.isReserved = true;
    }

    public void unreserve() {
        this.isReserved = false;
    }

    @Override
    public int compareTo(Table other){
    return Integer.compare(this.capacity, other.capacity);
    }


}
