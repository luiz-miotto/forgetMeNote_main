package com.example.forgetmenote.messaging;

import com.example.forgetmenote.Emailing.EmailDetails;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name="messageDetails")
@Entity
@NoArgsConstructor
public class MessageDetails {

    //senderEmail, senderId, recipientEmail, subject, body, attachment, messageId,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long messageId;

    private String senderEmail;
    private long senderId;
    private long recipientId;
    private long eventId;
    private String recipientEmail;
    private String messageSubject;
    private String messageBody;
    private String attachment;
    private String sendTime;

    public MessageDetails(EmailDetails emailDetails){
        this.messageBody = emailDetails.getMessageBody();
        this.messageSubject = emailDetails.getSubject();
        this.attachment = emailDetails.getAttachment();
        this.recipientEmail = emailDetails.getRecipient();
    }



}
