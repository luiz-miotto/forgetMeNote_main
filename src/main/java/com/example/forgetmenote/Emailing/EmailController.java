package com.example.forgetmenote.Emailing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emailing")
public class EmailController {

    @Autowired
    private EmailService emailService;

    //@RequestBody allows Spring to auto deserialize the json in the request body
    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody EmailDetails emailDetails){
        String status = emailService.sendSimpleEmail(emailDetails);
        return status;
    }
}
