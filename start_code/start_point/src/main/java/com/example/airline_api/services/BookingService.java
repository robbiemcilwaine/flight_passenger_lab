package com.example.airline_api.services;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.BookingDTO;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerService passengerService;

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public void saveBooking(Booking booking){
        bookingRepository.save(booking);
    }

    public Booking book(Booking booking){
//        create passenger and flight objects to instantiate booking with
//        Passenger passenger = passengerService.getSinglePassenger(booking.getPassenger().getId()).get();
//        passengerService.addPassenger(passenger);
//        Flight flight = flightService.getSingleFlight(booking.getFlight().getId()).get();
//        flightService.saveFlight(flight);
        Flight flight;
        Passenger passenger;
        Booking newBooking;

        Optional<Flight> optionalFlight = flightService.getSingleFlight(booking.getFlight().getId());
        Optional<Passenger> optionalPassenger = passengerService.getSinglePassenger(booking.getPassenger().getId());

        if (optionalFlight.isPresent() && optionalPassenger.isPresent()){
            flight = optionalFlight.get();
            passenger = optionalPassenger.get();

            newBooking = new Booking(flight, passenger, booking.getSeatNumber());
            optionalFlight.get().setCapacity(flight.getCapacity()-1);
            bookingRepository.save(newBooking);
        }
        return newBooking;
    }

}
