package com.example.airline_api.components;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.BookingDTO;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.BookingRepository;
import com.example.airline_api.services.BookingService;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerService passengerService;

    @Autowired
    BookingRepository bookingRepository;

    public DataLoader(){

    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        Flight newYork = new Flight("JFK", 136, "2024-06-12G22", "13:00");
        Flight losAngeles = new Flight("LAX", 150, "2024-06-15T10", "00:00");
        flightService.saveFlight(newYork);
        flightService.saveFlight(losAngeles);

        Passenger robbie = new Passenger("Robbie", "robbiemcilwaine99@gmail.com");
        passengerService.addPassenger(robbie);

        Passenger yavin = new Passenger("Yavin", "ysongra@gmail.com");
        passengerService.addPassenger(yavin);

        Passenger fergus = new Passenger("Fergus", "fergusmc99@gmail.com");
        passengerService.addPassenger(fergus);

//        seed db with flight and passenger for adding booking
        Flight belfast = new Flight("BHD", 112, "2024-06-16TH20", "12:40");
        flightService.saveFlight(belfast);

//        don't create booking for objects with id of 3
        Booking booking1 = new Booking(newYork, robbie, 1);
        Booking booking2 = new Booking(losAngeles, yavin, 1);
        bookingRepository.save(booking1);
        bookingRepository.save(booking2);
    }
}
