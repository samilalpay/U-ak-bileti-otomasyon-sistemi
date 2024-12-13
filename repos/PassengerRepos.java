package com.samilemir.repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.samilemir.model.Passenger;

public interface PassengerRepos extends JpaRepository<Passenger, Long> {

  
}
