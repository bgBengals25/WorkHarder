package com.toth.workharder;

import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Interval implements Serializable {

    private ArrayList<Exercise> exercises;
    private int startExercise;
    private int currentExercise;
    private int repetitions;
    private int originalRepetitions;

    private boolean finished = false;

    private String name;

    public Interval(int repetitions){
        this.exercises = new ArrayList<Exercise>();
        startExercise = 0;
        currentExercise = startExercise;
        this.name = "";
        this.repetitions = repetitions-1;
        this.originalRepetitions = this.repetitions;
    }

    public Interval(String name){
        this.exercises = new ArrayList<Exercise>();
        startExercise = 0;
        currentExercise = startExercise;
        this.name = name;
    }

    public Interval(String name, int startExercise){
        this.exercises = new ArrayList<Exercise>();
        this.startExercise = startExercise;
        currentExercise = startExercise;
        this.name = name;
    }

    public Interval(String name, Exercise[] exercises){
        this.exercises = new ArrayList<Exercise>(Arrays.asList(exercises));
        startExercise = 0;
        currentExercise = startExercise;
        this.name = name;
    }

    public Interval(String name, Exercise[] exercises, int startExercise){
        this.exercises = new ArrayList<Exercise>(Arrays.asList(exercises));
        this.startExercise = startExercise;
        currentExercise = startExercise;
        this.name = name;
    }

    public void addExercise(Exercise exercise){
        this.exercises.add(exercise);
    }

    public int getNumExercises(){
        return exercises.size();
    }

    public Exercise getCurrentExercise(){
        return exercises.get(currentExercise);
    }

    public void nextExercise(){
        if(currentExercise == getNumExercises()-1)
        {
            if(repetitions > 0) {
                repetitions--;
                currentExercise = 0;
                return;
            }else{
                currentExercise = 0;
                finished = true;
                return;
            }
        }
        currentExercise++;
    }

    public boolean isFinished(){
        return finished;
    }

    public void setFinished(boolean finished){
        this.finished = finished;
    }

    public void resetWorkout(){
        currentExercise = startExercise;
        repetitions = originalRepetitions;
        finished = false;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
