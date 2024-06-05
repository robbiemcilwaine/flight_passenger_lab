package com.example.airline_api.services;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.BookingDTO;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Booking bookPassenger(BookingDTO bookingDTO, Long id){
        Passenger passenger = passengerService.getSinglePassenger(bookingDTO.getPassenger().getId()).get();
        passengerService.addPassenger(passenger);
        Flight flight = flightService.getSingleFlight(bookingDTO.getFlight().getId()).get();
        flightService.saveFlight(flight);

        Booking booking = new Booking(flight, passenger, bookingDTO.getSeatNumber());
        flight.setCapacity(flight.getCapacity()-1);
        bookingRepository.save(booking);
        return booking;
    }

}
