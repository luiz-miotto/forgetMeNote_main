package com.example.forgetmenote.repositories;

import com.example.forgetmenote.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

//@Component
@Repository
public interface EventRepository extends JpaRepository<Event,String> {

}
