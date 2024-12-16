package com.samilemir.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="flights")
public class Flight extends BaseEntity {

    @Column(name="departure_time", nullable=false)
    private LocalDateTime departureTime;

    @Column(name="arrival_time", nullable=false)
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name="departure_airport_id", nullable=false)
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name="arrival_airport_id", nullable=false)
    private Airport arrivalAirport;

    // Getter ve Setter
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
}
