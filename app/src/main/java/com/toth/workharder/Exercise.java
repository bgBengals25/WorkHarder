package com.toth.workharder;

import java.io.Serializable;

public class Exercise implements Serializable {

    private String name;
    private int duration; // seconds

    public Exercise(String name, int duration){
        this.name = name;
        this.duration = duration;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public String getName(){
        return name;
    }

    public int getDuration(){
        return duration;
    }
}
