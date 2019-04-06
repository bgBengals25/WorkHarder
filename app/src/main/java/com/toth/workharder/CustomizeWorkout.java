package com.toth.workharder;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomizeWorkout extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ArrayList<Model> exerciseList = new ArrayList<>();
    private ModelAdapter mAdapter;

    private TextView enterName, enterTime, repetitions;
    private Button addButton, startWorkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_workout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //exerciseList.add(new Model("running", 15));
        //exerciseList.add(new Model("jumping jacks", 15));
        //exerciseList.add(new Model("rest", 15));

        mAdapter = new ModelAdapter(exerciseList);
        recyclerView.setAdapter(mAdapter);

        enterName = (TextView) findViewById(R.id.etxt_name);
        enterTime = (TextView) findViewById(R.id.etxt_seconds);
        addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enterName.getText().length() > 0 && enterTime.length() > 0) {
                    mAdapter.addItemToList(new Model(enterName.getText().toString(), Integer.parseInt(enterTime.getText().toString())));
                    enterName.setText("");
                    enterTime.setText("");
                    enterName.requestFocus();
                }else{
                    Snackbar.make(v, "You're missing something...", Snackbar.LENGTH_SHORT);
                }
            }
        });

        repetitions = (TextView) findViewById(R.id.repeat);
        startWorkoutButton = (Button) findViewById(R.id.startWorkoutButton);

        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repetitions.getText().length() > 0 && mAdapter.getItemCount() > 0)
                {
                    Interval interval = new Interval(Integer.parseInt(repetitions.getText().toString()));
                    for(Model m : mAdapter.getItems()){
                        interval.addExercise(new Exercise(m.getName(), m.getSeconds()));
                    }
                    Intent intent = new Intent("android.intent.action.WorkoutActivity");
                    intent.putExtra("interval", interval);
                    startActivity(intent);
                }
            }
        });

    }
}
