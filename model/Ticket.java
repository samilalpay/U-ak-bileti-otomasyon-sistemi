package com.samilemir.model;

import java.math.BigDecimal;

import com.samilemir.enums.CurrencyType;
import com.samilemir.enums.TicketStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Ticket extends BaseEntity {
	
	@Column(name="seat_number", nullable=false)
	private String seatNumber;
	
	@Column(name="amount", nullable=false)
	private BigDecimal amount;
	
	@Column(name="currency_type", nullable=false)
	@Enumerated(EnumType.STRING)
	private CurrencyType currencyType;
	
	@Column(name="ticket_status", nullable=false)
	@Enumerated(EnumType.STRING)
	private TicketStatus ticketStatus;
	
	@ManyToOne
	@JoinColumn(name="passenger_id", nullable=false)
	private Passenger passenger;
	
	@ManyToOne
	@JoinColumn(name="flight_id", nullable=false)
	private Flights flight;
	
	

}
