package com.samilemir.controller;

import com.samilemir.model.Flight;
import com.samilemir.model.Airport;
import com.samilemir.service.IFlightService;
import com.samilemir.service.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    @Autowired
    private IFlightService flightService;

    @Autowired
    private IAirportService airportService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new AccessDeniedException("Authorization token is missing or invalid");
        }
        Airport departureAirport = airportService.getAirportById(flight.getDepartureAirport().getId());
        Airport arrivalAirport = airportService.getAirportById(flight.getArrivalAirport().getId());

        if (departureAirport == null || arrivalAirport == null) {
            throw new IllegalArgumentException("One of the airports does not exist");
        }

        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        
        Flight createdFlight = flightService.createFlight(flight);
        return createdFlight;
    }

    @PutMapping("/{id}")
    public Flight updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        return flightService.updateFlight(id, flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}
