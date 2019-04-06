package com.toth.workharder;

import java.io.Serializable;

public class Model implements Serializable {

    private String name;
    private int seconds;

    public Model(String name, int seconds) {
        this.name = name;
        this.seconds = seconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}