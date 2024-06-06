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
import java.util.Objects;
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Booking>> getBookingById(@PathVariable long id){
        return new ResponseEntity<>(bookingService.getBookingById(id), HttpStatus.OK);
    }

//    bookingDTO used as this minimises input needed to id
//    without a DTO, JSON objects are required as input as the attributes of the Booking model
    // TODO: Add details of a new booking
    @PostMapping
    public ResponseEntity<Booking> addNewBooking(@RequestBody BookingDTO bookingDTO){
        Booking booking = bookingService.book(bookingDTO);
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    // TODO: Extension - Update passenger meal preference
//    takes id in as path, request param equals new meal preference
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Booking> updateMealPreference(@PathVariable long id, @RequestParam(defaultValue = "Standard Meal") String mealPreference) {
        Booking booking = bookingService.getBookingById(id).get();
        booking.setMealPreference(mealPreference);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

}
