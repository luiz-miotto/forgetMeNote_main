package com.example.forgetmenote.models;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

public class UserNotificationHistory {
    //userId, username, emailAddress, phoneNumber, messageId, messageTime, notificationType
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String emailAddress;
    private String phoneNumber;
    private long messageId;
    //private enum UserNotificationHistory.NotificationType notificationType;

    public enum NotificationType{
        SMS,
        EMAIL,
    }


    public UserNotificationHistory(){

    }
}
