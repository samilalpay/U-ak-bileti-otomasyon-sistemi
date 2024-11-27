package model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name="passenger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Passenger extends BaseEntity {
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="lastName", nullable=false)
	private String lastName;
	
	@Column(name="birth_of_date", nullable=false)
	private LocalDate birthOfDate;
	
	@Column(name="email", nullable=false, unique=true)
	private String mail;
	
	@Column(name="telephone_number", nullable=false)
	@Size(min=10, max=15)
	private Long telephoneNumber;
	
	
	
	

}
