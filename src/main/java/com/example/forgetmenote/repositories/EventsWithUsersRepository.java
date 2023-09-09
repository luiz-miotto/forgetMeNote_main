package com.example.forgetmenote.repositories;

import com.example.forgetmenote.models.EventsWithUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsWithUsersRepository extends JpaRepository<EventsWithUsers, String> {


}
