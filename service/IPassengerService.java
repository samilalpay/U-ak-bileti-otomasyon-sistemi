package com.samilemir.service;


import java.util.List;

import com.samilemir.model.Passenger;

public interface IPassengerService {

    List<Passenger> getAllPassengers();

    Passenger getPassengerById(Long id);

    Passenger createPassenger(Passenger passenger);

    Passenger updatePassenger(Long id, Passenger passengerDetails);

    void deletePassenger(Long id);
    
}
