package com.example.airline_api.services;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.Flight;
import com.example.airline_api.repositories.BookingRepository;
import com.example.airline_api.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    BookingRepository bookingRepository;

    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getSingleFlight(long id) {
        return flightRepository.findById(id);
    }

    public void deleteFlight(long flightId) {
        Optional<Flight> optionalFlight = getSingleFlight(flightId);
        if (optionalFlight.isPresent()) {
            Flight flight = optionalFlight.get();

//        must delete associated bookings with the flight
//            bookings cannot exist for flights that no longer exist
            for (Booking booking : flight.getBookings()) {
                bookingRepository.delete(booking);
            }

            // Delete the flight
            flightRepository.delete(flight);
        }

    }
}
