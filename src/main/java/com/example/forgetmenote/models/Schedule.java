package com.example.forgetmenote.models;

import java.util.List;

public class Schedule {
    private List<Event> eventsList;
    private String nameOfSchedule;

    public Schedule(String nameOfSchedule){
        this.nameOfSchedule = nameOfSchedule;
    }

    public Schedule(){

    }

    public String getName(){
        return this.nameOfSchedule;
    }

}
