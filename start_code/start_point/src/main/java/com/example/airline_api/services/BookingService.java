package com.example.airline_api.services;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.BookingDTO;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerService passengerService;

    public void bookPassenger(BookingDTO bookingDTO){
        Passenger passenger = passengerService.getSinglePassenger(bookingDTO.getPassenger().getId()).get();
        Flight flight = flightService.getSingleFlight(bookingDTO.getFlight().getId()).get();

        Booking booking = new Booking(flight, passenger, bookingDTO.getSeatNumber());
        flight.setCapacity(flight.getCapacity()-1);
    }

}
