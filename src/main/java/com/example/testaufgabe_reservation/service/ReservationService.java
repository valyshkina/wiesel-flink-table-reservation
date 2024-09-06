package com.example.testaufgabe_reservation.service;

import com.example.testaufgabe_reservation.model.Restaurant;
import com.example.testaufgabe_reservation.model.Table;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class ReservationService {
    private List<Restaurant> restaurants;


    public ReservationService() {

        List<Table> tables1 = new ArrayList<>();
        tables1.add(new Table(1, 4, "0"));
        tables1.add(new Table(2, 6, "Lisa mit Hut", 1894));
        tables1.add(new Table(3, 3, "0"));
        tables1.add(new Table(4, 2, "Paul mit Brille", 3611));
        Collections.sort(tables1);


        List<Table> tables2 = new ArrayList<>();
        tables2.add(new Table(1, 2, "Sarah mit langen Haaren", 9739));
        tables2.add(new Table(2, 5, "0"));
        tables2.add(new Table(3, 4, "Tom mit Bart", 4801));
        tables2.add(new Table(4, 6, "0"));
        Collections.sort(tables2);


        List<Table> tables3 = new ArrayList<>();
        tables3.add(new Table(1, 3, "Anna mit rotem Schal",6191));
        tables3.add(new Table(2, 1, "Peter ohne Freunde", 1952));
        tables3.add(new Table(3, 4, "0"));
        tables3.add(new Table(4, 2, "Heike mit Sonnenbrille", 3755 ));
        Collections.sort(tables3);

        restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("Wiesel-Flink-Food Hamburg", tables1));
        restaurants.add(new Restaurant("Wiesel-Flink-Food Lünen", tables2));
        restaurants.add(new Restaurant("Wiesel-Flink-Food Dresden", tables3));
    }

    public List<Table> getTables(Restaurant restaurant ) {
        return restaurant.getTables();
    }

    public boolean isTableAvailable(Restaurant restaurant, int capacity) {
        for(Table table : restaurant.getTables()){
            if(table.getCapacity() >= capacity && table.getReservedBy().equals("0")){
                return true;
            }
        }
        return false;
    }


    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public int reserve(Restaurant restaurant, int capacity, String reservedBy) {
        if (isTableAvailable(restaurant, capacity) && capacity > 0) {
            for (Table table : restaurant.getTables()) {
                if (table.getCapacity() >= capacity && table.getReservedBy().equals("0")) {
                    table.setReservedBy(reservedBy);
                    table.reserve();
                    int reservationNumber = new Random().nextInt(9000) + 1000;
                    table.setReservationNumber(reservationNumber);
                    return reservationNumber;
                }
            }
        }
        return 0;
    }

    public Restaurant findRestaurantByName(String restaurantName) {
        for (Restaurant r : restaurants) {
            if (r.getName().equalsIgnoreCase(restaurantName)) {
                return r;
            }
        }
        return null;
    }

    public boolean cancelReservation(Restaurant restaurant, String reservedBy, int reservationNumber){
        for(Table table : restaurant.getTables()){
            if(table.getReservedBy().equalsIgnoreCase(reservedBy) && table.getReservationNumber() == reservationNumber){
                table.setReservedBy("0");
                table.setReserved(false);
                table.setReservationNumber(0);
                return true;
            }
        }
        return false;

    }

    public void resetAllReservations(Restaurant restaurant){
        for(Table table : restaurant.getTables()){
            table.setReservedBy("0");
            table.setReserved(false);
            table.setReservationNumber(0);
        }



    }

    public int getMaxCapacity(Restaurant restaurant){
        int maxCapacity = 0;
        for(Table table : restaurant.getTables()){
            if(table.getCapacity() > maxCapacity){
                maxCapacity = table.getCapacity();
            }
        }
        return maxCapacity;
    }







}
