package com.example.wlogger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener, listener{
    List<Exercise> l1 = new ArrayList<>();
    List<User> l2 = new ArrayList<>();
    List<String> name = new ArrayList<>();
    List<Integer> image = new ArrayList<>();
    List<String> ppl = new ArrayList<>();
    List<String> part = new ArrayList<>();
    RecyclerView list1;
    RecyclerView list2;
    EditText search1;
    EditText search2;
    ImageButton button1;
    ImageButton button2;
    static String value = "";
    static String email = "";
    static long count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchactivity);
        init();
        list1 = findViewById(R.id.listuser);
        list2 = findViewById(R.id.listexercise);
        search1 = findViewById(R.id.textuser);
        search2 = findViewById(R.id.textexercise);
        button1 = findViewById(R.id.searchuser);
        button2 = findViewById(R.id.searchexercise);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        l1.clear();
        l2.clear();
        super.onResume();
    }

    public void show(){
        Adapter adapter = new Adapter(this, l1, this);
        list1.setAdapter(adapter);
        list1.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter adapter1 = new UserAdapter(this, l2, this);
        list2.setAdapter(adapter1);
        list2.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.searchuser){
            l1 = new ArrayList<>();
            l2 = new ArrayList<>();
            EditText t = findViewById(R.id.textuser);
            value = t.getText().toString().trim().toLowerCase();
            if(value.equals("") || value.equals(null)){
                return;
            }
            t.setText("");
            //FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    l1.clear();l2.clear();
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String username = userSnapshot.child("username").getValue(String.class).trim();
                        String dateOfJoining = userSnapshot.child("Date").getValue(String.class).trim();
                        String UID = userSnapshot.getKey();

                        DatabaseReference logsReference = databaseReference.child(UID).child("Logs");
                        logsReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                                long count = dataSnapshot1.getChildrenCount();
                                if (find(username, value) && !UID.equals(MainActivity.userId)) {
                                    l2.add(new User(username, count, dateOfJoining, R.drawable.ic_launcher_foreground, UID));
                                    show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError1) {
                                if (find(username, value)&& !UID.equals(MainActivity.userId)) {
                                    l2.add(new User(username, 0, dateOfJoining, R.drawable.ic_launcher_foreground, UID));
                                    show();
                                }
                            }
                        });
                    }
                    //show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle the error if necessary
                }
            });
        }
        else if (v.getId() == R.id.searchexercise){
            EditText text = findViewById(R.id.textexercise);
            value = text.getText().toString().trim().toLowerCase();
            if(value.equals("") || value.equals(null)){
                return;
            }
            l1 = new ArrayList<>();
            l2 = new ArrayList<>();
            for(int i=0;i<name.size();i++){
                if(name.get(i).toLowerCase().contains(value)){
                    l1.add(new Exercise(name.get(i), image.get(i), ppl.get(i), part.get(i)));
                }
            }
            show();
        }
    }

    public void init(){
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
        // change this later
        for(i=0;i<name.size();i++){
            image.add(R.drawable.ic_launcher_foreground);
        }
    }

    @Override
    public void OnItemClick(int position, int source) {
        ProfileStalking.userId = l2.get(position).getID();
        if(ProfileStalking.userId.equals(MainActivity.userId)){

        }
        else {
            Intent intent = new Intent(SearchActivity.this, ProfileStalking.class);
            startActivity(intent);
        }
    }

    public boolean find(String username, String value){
        username = username.toLowerCase().trim();
        value = value.toLowerCase().trim();
        if(value.length() > username.length()){
            return false;
        }
        String match = username.substring(0, value.length());
        if(match.equalsIgnoreCase(value)){
            return true;
        }
        else{
            return false;
        }
    }
}