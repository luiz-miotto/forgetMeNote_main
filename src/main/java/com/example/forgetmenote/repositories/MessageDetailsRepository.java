package com.example.forgetmenote.repositories;

import com.example.forgetmenote.messaging.MessageDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageDetailsRepository extends JpaRepository<MessageDetails,String> {

    //Optional<MessageDetails> findByName(String name);
    Optional<MessageDetails> findByMessageId(Long messageId);
}




