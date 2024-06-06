package com.example.airline_api.services;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.BookingDTO;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.BookingRepository;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PassengerRepository passengerRepository;

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(long id){
        return bookingRepository.findById(id);
    }

    public void saveBooking(Booking booking){
        bookingRepository.save(booking);
    }

    public Booking book(BookingDTO bookingDTO){
//        create passenger and flight objects to instantiate booking with

        Flight flight = flightRepository.findById(bookingDTO.getFlightId()).get();
        Passenger passenger = passengerRepository.findById(bookingDTO.getPassengerId()).get();

        Booking booking = new Booking(flight, passenger, bookingDTO.getSeatNumber());
        return bookingRepository.save(booking);
    }
}
