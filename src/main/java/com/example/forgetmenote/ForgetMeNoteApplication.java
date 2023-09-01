package com.example.forgetmenote;

import com.example.forgetmenote.models.Event;
import com.example.forgetmenote.models.User;
import com.example.forgetmenote.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.forgetmenote.repositories.EventRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
//@ComponentScan({"com.example.forgetmenote"})
public class ForgetMeNoteApplication implements CommandLineRunner {

    //Mongo db password is Mongodblumi27@
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public ForgetMeNoteApplication(EventRepository eventRepository, UserRepository userRepository){
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ForgetMeNoteApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        /*
       List<Event> eventList = eventRepository.findAll();
       for(Event event: eventList ){
           System.out.println(event.getName());
       }
        */
       //List<User> userList = userRepository.findAll();

       /* current execeptions are Caused by: org.springframework.dao.InvalidDataAccessResourceUsageException: JDBC exception executing SQL [select u1_0.id,u1_0.email_address,u1_0.name,u1_0.password,u1_0.username from user u1_0] [ERROR: column u1_0.id does not exist
  Position: 8] [n/a]; SQL [n/a]

       and

       [ERROR: syntax error at or near "user" Position: 13] [insert into user (email_address,name,password,username) values (?,?,?,?)] postgres
        */
        /*

        String sql = "INSERT INTO event (name, description, event_Type) VALUES ("
                + "'luiz miotto3', 'luiz@whatever.net', 'WORK_EVENT')";
        int rows = jdbcTemplate.update(sql);
        if (rows > 0) {
            System.out.println("A new row has been inserted.");
        }

        */
        /*

        String sqlTwo = "INSERT INTO event_user (name, username, password, email_address) VALUES ("
                + " 'luiz miotto2', 'luiz@whatever.net', 'Pass123','luiz@whatever.net')";
        int rowsTwo = jdbcTemplate.update(sqlTwo);
        if (rowsTwo > 0) {
            System.out.println("A new row has been inserted.");
        }

         */

    }
}
