package com.toth.workharder;

public class WorkoutExercise {

    private String name;
    private int workoutMinutes;
    private int workoutSeconds;

    public WorkoutExercise() {
        name = "";
        workoutMinutes = 0;
        workoutSeconds = 0;
    }

    public WorkoutExercise(String name, int workoutMinutes, int workoutSeconds) {
        this.name = name;
        this.workoutMinutes = workoutMinutes;
        this.workoutSeconds = workoutSeconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWorkoutMinutes() {
        return workoutMinutes;
    }

    public void setWorkoutMinutes(int workoutMinutes) {
        this.workoutMinutes = workoutMinutes;
    }

    public int getWorkoutSeconds() {
        return workoutSeconds;
    }

    public void setWorkoutSeconds(int workoutSeconds) {
        this.workoutSeconds = workoutSeconds;
    }
}
