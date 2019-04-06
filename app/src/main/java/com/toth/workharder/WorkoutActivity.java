package com.toth.workharder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WorkoutActivity extends AppCompatActivity {


    private TextView timerText;
    private TextView exerciseText;
    private TextView setsLeft;
    private Button startButton;
    private Button pausePlayButton;
    private Button resetButton;
    private Button exitButton;

    private Interval interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        timerText = findViewById(R.id.timerText);
        exerciseText = findViewById(R.id.exerciseName);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);
        setsLeft = findViewById(R.id.setsLeft);

        interval = (Interval) getIntent().getSerializableExtra("interval");

        exerciseText.setText(interval.getCurrentExercise().getName());
        setsLeft.setText("Sets left: "+(interval.getRepetitions()+1));

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startTimer(v);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer(v);
            }
        });

        exitButton = (Button) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                builder.setMessage("Exit Workout?")
                        .setCancelable(false)
                        .setPositiveButton("CONFIRM",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                                    }
                                })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                            }
                        });

                builder.show();
            }
        });
    }

    public void startTimer(View button) throws InterruptedException {

        class TimerTask extends AsyncTask<Integer, Double, String> {

            Context context;

            public TimerTask(Context context){
                this.context = context;
            }

            @Override
            protected String doInBackground(Integer... integers) {

                double goal = interval.getCurrentExercise().getDuration();
                double num = 0;
                long time = System.currentTimeMillis();

                while(true){

                    num = (System.currentTimeMillis() - time) / 1000.0;

                    if(goal - num <= 0){
                        publishProgress(0.0);
                        if(!interval.isFinished()) {
                            return null;
                        }else{

                            this.cancel(true);
                        }
                    }

                    publishProgress(goal - num);

                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void onProgressUpdate(Double... values) {
                super.onProgressUpdate(values);

                timerText.setText(String.format("%.2f", values));
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                MediaPlayer.create(getApplicationContext(), R.raw.beep).start();
                interval.nextExercise();

                if(!interval.isFinished()) {
                    exerciseText.setText(interval.getCurrentExercise().getName());
                    setsLeft.setText("Sets left: "+(interval.getRepetitions()+1));
                    try {
                        new TimerTask(getApplicationContext()).execute();
                    }catch(Exception e){

                    }
                }else{
                    exerciseText.setText("Workout Finished!");
                    setsLeft.setText("Sets left: 0");
                    MediaPlayer.create(getApplicationContext(), R.raw.beep).start();
                    MediaPlayer.create(getApplicationContext(), R.raw.beep).start();
                    MediaPlayer.create(getApplicationContext(), R.raw.beep).start();
                    this.cancel(true);
                }
            }
        }

        exerciseText.setText(interval.getCurrentExercise().getName());
        new TimerTask(this).execute();
    }

    public void resetTimer(View button){
        if(interval.isFinished()) {
            interval.resetWorkout();
            exerciseText.setText(interval.getCurrentExercise().getName());
            setsLeft.setText("Sets left: " + (interval.getRepetitions() + 1));
            timerText.setText("0.00");
        }
    }
}
