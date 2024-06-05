package com.example.airline_api.controllers;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.BookingDTO;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.services.BookingService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    @Autowired
    PassengerService passengerService;

    // TODO: Display all bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings(){
        return new ResponseEntity<>(bookingService.getAllBookings(), HttpStatus.OK);
    }

    // TODO: Add details of a new booking
//    removed id from method parameters
//    Booking instead of bookingDTO
    @PostMapping
    public ResponseEntity<Booking> addNewBooking(@RequestBody Booking booking){
        Booking savedBooking = bookingService.book(booking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }

    // TODO: Extension - Update passenger meal preference
    @PatchMapping
    public ResponseEntity<Booking> updateMealPreference(){
        return null;
    }

}
