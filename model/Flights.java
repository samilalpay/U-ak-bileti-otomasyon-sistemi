package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Flights extends BaseEntity {
	
	@Column(name="departure_time", nullable=false)
	private Long departureTime;
	
	@Column(name="arrival_time", nullable=false)
	private Long arrivaltime;
	
	@ManyToOne
	@JoinColumn(name="departure_airport_id", nullable=false)
	private Airport departureAirport;
	
	@ManyToOne
	@JoinColumn(name="arrival_airport_id", nullable=false)
	private Airport arrivalAirport;

}
