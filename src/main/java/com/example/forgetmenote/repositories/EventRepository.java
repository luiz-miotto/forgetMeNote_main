package com.example.forgetmenote.repositories;

import com.example.forgetmenote.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Component
@Repository
public interface EventRepository extends JpaRepository<Event,String> {

    Optional<Event> findByName(String name);
    Optional<Event> findById(Long id);



}

interface idsOnly{

        }
