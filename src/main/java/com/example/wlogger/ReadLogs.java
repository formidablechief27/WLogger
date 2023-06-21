package com.example.wlogger;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReadLogs extends AppCompatActivity {
    static String userId="";
    static String date="";
    RecyclerView recyclerView;

    ArrayList<LogGet> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readlogs);
        recyclerView = findViewById(R.id.loglist);
        fill();
    }

    public void show(){
        Adapader adapter = new Adapader(this, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void fill(){
        String logsNode = "Logs/" + date;
        DatabaseReference logsRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId).child(logsNode);

        logsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int count = 0;
                    for (DataSnapshot logSnapshot : dataSnapshot.getChildren()) {
                        String logEntry = logSnapshot.getValue(String.class);
                        String[] logValues = logEntry.split(" ");
                        String name = "";
                        for(int i=0;i<=logValues.length-4;i++){
                            name += logValues[i] + " ";
                        }
                        name.trim();
                        String weight = logValues[logValues.length-3];
                        String reps = logValues[logValues.length-2];
                        count++;
                        list.add(new LogGet(R.drawable.ic_launcher_foreground, name, weight, reps, String.valueOf(count)));
                        show();

                    }
                    //show(); // Call the show() method here to update the RecyclerView after data retrieval
                } else {
                    Toast.makeText(getApplicationContext(), "You have no logs today", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReadLogs.this, ProfileActivity.class);
                    startActivity(intent);
                    finish(); // Finish the activity to prevent going back to it without valid logs
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error appropriately
            }
        });
    }
}
