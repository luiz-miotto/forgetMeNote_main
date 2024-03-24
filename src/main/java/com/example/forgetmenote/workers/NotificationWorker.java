package com.example.forgetmenote.workers;

public class NotificationWorker {

    // worker is constantly running and checking in certain intervals (1 minute)
    // when worker runs it will check if any reminders are due to be sent out in the next minute
    // since we have a table for notifications in database, we can query the database for any notification whose message time is within 1 minute in the future
    //this then means we will need to add an additional column where the notification time will be stored

    //March 16th note:
    // separate project to handle notification server. Wil need to have a worker constantly running
    // Will need to determine the intervalic frequency that worker will check for notifications
    //this invervalic frequency will be determined by the lowest commmon denominator/level of prceision regarding scheduling times
    //i.e. events can be scheduled for hourly intervals, daily intervals, or 15 minute increment intervals
}
