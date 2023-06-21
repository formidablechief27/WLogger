package com.example.wlogger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LogActivity extends AppCompatActivity implements View.OnClickListener, listener {

    List<String> name = new ArrayList<>();
    List<Integer> image = new ArrayList<>();
    List<String> ppl = new ArrayList<>();
    List<String> part = new ArrayList<>();
    List<Exercise> list = new ArrayList<Exercise>();
    ImageButton search;
    RecyclerView recyclerView;

    RecyclerView view;

    Button readlog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loglist);
        recyclerView = findViewById(R.id.list);
        search = findViewById(R.id.search);
        readlog = findViewById(R.id.readlog);
        fill();
        show();
        search.setOnClickListener(this);
        readlog.setOnClickListener(this);
    }

    public void show(){
        Adapter adapter = new Adapter(this, list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.readlog){
            Intent intent = new Intent(LogActivity.this, ReadLog.class);
            startActivity(intent);
        }
        list = new ArrayList<Exercise>();
        EditText text = findViewById(R.id.papo);
        String value = text.getText().toString().trim().toLowerCase();
        if(value.equals(null) || value.equals("")){
            fill();
            show();
        }
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i=0;i<name.size();i++){
            if(name.get(i).toLowerCase().contains(value)){
                list.add(new Exercise(name.get(i), image.get(i), ppl.get(i), part.get(i)));
                set.add(i);
            }
        }
        for(int i=0;i<name.size();i++){
            if(set.contains(i)){
                continue;
            }
            Exercise E = new Exercise(name.get(i), image.get(i), ppl.get(i), part.get(i));
            if(list.indexOf(E)!=-1){
                continue;
            }
            if(part.get(i).toLowerCase().contains(value)){
                list.add(E);
                set.add(i);
            }
        }
        for(int i=0;i<name.size();i++){
            if(set.contains(i)){
                continue;
            }
            Exercise E = new Exercise(name.get(i), image.get(i), ppl.get(i), part.get(i));
            if(list.indexOf(E)!=-1){
                continue;
            }
            if(ppl.get(i).toLowerCase().contains(value)){
                list.add(E);
                set.add(i);
            }
        }
        show();
    }

    public void fill(){
        name.add("Deadlift");
        name.add("Sumo Deadlift");
        name.add("Romanian Deadlift");
        name.add("Trap Bar Deadlift");
        name.add("Pullups");
        name.add("Chinups");
        name.add("Neutral Grip Pullups");
        name.add("Mixed Grip Pullups");
        name.add("Wide Grip Pullups");
        name.add("One arm Pullups");
        name.add("Barbell Rows");
        name.add("Cable Rows");
        name.add("Single Arm Rows");
        name.add("Lat Pulldown");
        name.add("Face Pulls");
        int i = 0;
        for(i=0;i<name.size();i++){
            ppl.add("Pull");
            part.add("Back");
        }
        name.add("Bicep Curls");
        name.add("Preacher Curls");
        name.add("Hammer Curls");
        name.add("Neutral Curls");
        name.add("Spider Curls");
        name.add("Incline Curls");
        for(;i<name.size();i++){
            ppl.add("Pull");
            part.add("Biceps");
        }
        name.add("Bench Press");
        name.add("Incline Bench Press");
        name.add("Decline Bench Press");
        name.add("Dumbell Bench Press");
        name.add("Dumbell Incline Bench Press");
        name.add("Dumbell Decline Bench Press");
        name.add("Chest Press");
        name.add("Chest Flies");
        name.add("Lower Chest Flies");
        name.add("Upper Chest Flies");
        name.add("Dips");
        name.add("Pushups");
        name.add("Incline Pushups");
        name.add("Decline Pushups");
        name.add("Wide Pushups");
        name.add("Explosive Pushups");
        name.add("Archer Pushups");
        name.add("One Arm Pushups");
        for(;i<name.size();i++){
            ppl.add("Push");
            part.add("Chest");
        }
        name.add("Shoulder Press");
        name.add("Arnold Press");
        name.add("Dumbell Shoulder Press");
        name.add("Front Raises");
        name.add("Lateral Raises");
        name.add("Pike Pushups");
        name.add("Handstand Pushups");
        for(;i<name.size();i++){
            ppl.add("Push");
            part.add("Shoulder");
        }
        name.add("Diamond Pushups");
        name.add("Close grip Bench Press");
        name.add("Skull Crushers");
        name.add("Tricep Pushdown");
        name.add("Tricep Dips");
        name.add("Overhead Tricep Extension");
        for(;i<name.size();i++){
            ppl.add("Push");
            part.add("Triceps");
        }
        name.add("Squats");
        name.add("Front Squats");
        name.add("Lunges");
        name.add("Dumbell Lunges");
        name.add("Hack Squat");
        name.add("Pistol Squat");
        name.add("Leg Press");
        name.add("Bulgarian Split Squats");
        name.add("Leg Extension");
        name.add("Hamstring Curls");
        name.add("Glute Raises");
        name.add("Glute Hip Thrust");
        name.add("Romainian Deadlift");
        name.add("Calf Raises");
        for(;i<name.size();i++){
            ppl.add("Legs");
            part.add("Legs");
        }
        name.add("Skipping");
        name.add("Running");
        name.add("Jogging");
        name.add("Walking");
        name.add("Cycling");
        name.add("Swimming");
        name.add("Football");
        name.add("Cricket");
        for(;i<name.size();i++){
            ppl.add("Cardio");
            part.add("");
        }
        for(i=0;i<name.size();i++){
            image.add(R.drawable.ic_launcher_foreground);
        }
        for(i=0;i<name.size();i++){
            list.add(new Exercise(name.get(i), image.get(i), ppl.get(i), part.get(i)));
        }
    }

    @Override
    public void OnItemClick(int position, int source) {
        if (list.get(position).getPpl().equals("Cardio")) {
            Intent intent = new Intent(LogActivity.this, CardioLogger.class);
            intent.putExtra("Name", list.get(position).getName());
            intent.putExtra("Image", list.get(position).getImage());
            intent.putExtra("Ppl", list.get(position).getPpl());
            intent.putExtra("Part", list.get(position).getPart());
            startActivity(intent);
        } else {
            Intent intent = new Intent(LogActivity.this, WorkoutLogger.class);
            intent.putExtra("Name", list.get(position).getName());
            intent.putExtra("Image", list.get(position).getImage());
            intent.putExtra("Ppl", list.get(position).getPpl());
            intent.putExtra("Part", list.get(position).getPart());
            startActivity(intent);
        }
    }
}
