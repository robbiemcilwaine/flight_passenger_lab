package com.example.airline_api.services;

import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    public void addPassenger(Passenger passenger){
        passengerRepository.save(passenger);
    }

    public List<Passenger> getAllPassengers(){
        return passengerRepository.findAll();
    }

//    public Optional<Passenger> getSinglePassenger(long id){
//        return passengerRepository.findById(id);
//    }

//    instead of returning Optional, can call .get() on the passenger from repository
    public Passenger getSinglePassenger(long id){
        return passengerRepository.findById(id).get();
    }

}
