package com.example.testaufgabe_reservation.controller;

import com.example.testaufgabe_reservation.model.Restaurant;
import com.example.testaufgabe_reservation.model.Table;
import com.example.testaufgabe_reservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/allTables")
    public ResponseEntity getTables(@RequestParam String restaurantName){
        Restaurant restaurant = reservationService.findRestaurantByName(restaurantName);
        if(restaurant == null){
            return new ResponseEntity<>("Restaurant mit dem Namen" + restaurantName + "wurde nicht gefunden.", HttpStatus.NOT_FOUND);
        }
        List<Table> Tables = reservationService.getTables(restaurant);
        return new ResponseEntity<>(Tables, HttpStatus.OK);

    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants(){
        return reservationService.getRestaurants();
    }

    @GetMapping("/isTableAvailable")
    ResponseEntity<String> isTableAvailable(@RequestParam String restaurantName, int capacity){

        Restaurant restaurant = reservationService.findRestaurantByName(restaurantName);
        if(restaurant == null){
            return new ResponseEntity<>("Restaurant mit dem Namen" + restaurantName + "wurde nicht gefunden.", HttpStatus.NOT_FOUND);
        }
        boolean available = reservationService.isTableAvailable(restaurant, capacity);
        if(available){
        return new ResponseEntity<>("ein passender Tisch ist verfügbar.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("kein passender Tisch ist verfügbar.", HttpStatus.OK);
        }
    }

    @PostMapping("/reserve")
    ResponseEntity<String> reserve(@RequestParam String restaurantName, @RequestParam int capacity, @RequestParam String reservedBy){

       Restaurant restaurant = reservationService.findRestaurantByName(restaurantName);
        if(restaurant == null){
            return new ResponseEntity<>("Restaurant mit dem Namen" + restaurantName + "wurde nicht gefunden.", HttpStatus.NOT_FOUND);
        }

        int maxCapacity = reservationService.getMaxCapacity(restaurant);
        if(capacity < 1 || capacity > maxCapacity){
            return new ResponseEntity<>("Die gewünschte Kapazität von " + capacity + " liegt außerhalb der möglichen Kapazitäten. Die maximale Kapazität beträgt " + maxCapacity + ".", HttpStatus.BAD_REQUEST);

        }
       int result = reservationService.reserve(restaurant, capacity, reservedBy );
        if(result != 0){
            return new ResponseEntity<>("Reservierung erfolgreich, Ihre Reservierungsnummer  lautet:" + result + ".", HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>("Kein passender Tisch verfügbar.", HttpStatus.BAD_REQUEST);
       }

    }

    @PostMapping("/cancelReservation")
    ResponseEntity<String> cancelReservation(@RequestParam String restaurantName,@RequestParam String reservedBy, @RequestParam int reservationNumber){
        Restaurant restaurant = reservationService.findRestaurantByName(restaurantName);
        if(restaurant == null){
            return new ResponseEntity<>("Restaurant mit dem Namen" + restaurantName + "wurde nicht gefunden.", HttpStatus.NOT_FOUND);
        }

        boolean canceled = reservationService.cancelReservation(restaurant, reservedBy, reservationNumber);
        if(canceled){
            return new ResponseEntity<>("Reservierung wurde erfolgreich storniert.", HttpStatus.OK);
        } else {

    return new ResponseEntity<>("Reservierung nicht gefunden", HttpStatus.OK);    }
    }

    @PostMapping("/cancelAllReservations")
    ResponseEntity<String> cancelAllReservations(@RequestParam String restaurantName){
        Restaurant restaurant = reservationService.findRestaurantByName(restaurantName);
        if(restaurant == null){
            return new ResponseEntity<>("Restaurant mit dem Namen" + restaurantName + "wurde nicht gefunden.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Alle Reservierungen für das Restaurant" + restaurantName + "wurden erfolgreich zurückgesetzt." , HttpStatus.OK);

    }




}
