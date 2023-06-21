package com.example.wlogger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ReadLog extends AppCompatActivity implements listener {

    RecyclerView recyclerView;

    ArrayList<LogGet> list = new ArrayList<>();
    static int count = 0;
    static String todayLog = "";


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readlog);
        recyclerView = findViewById(R.id.loglist);
        fill();
    }

    public void show(){
        LogAdapter adapter = new LogAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void fill() {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String logsNode = "Logs/" + currentDate;
        DatabaseReference logsRef = FirebaseDatabase.getInstance().getReference().child("users").child(MainActivity.userId).child(logsNode);

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
                    show(); // Call the show() method here to update the RecyclerView after data retrieval
                } else {
                    Toast.makeText(getApplicationContext(), "You have no logs today", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ReadLog.this, LogActivity.class);
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

    @Override
    public void OnItemClick(int position, int source) {
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String filename = currentDate;
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(MainActivity.userId);

        userRef.child("Logs").child(filename).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<String> logs = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String logEntry = snapshot.getValue(String.class);
                        logs.add(logEntry);
                    }

                    if(logs.size() == 1){
                        userRef.child("Logs").child(filename).removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(ReadLog.this, "Entry Deleted ", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ReadLog.this, ReadLog.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // An error occurred while removing the Logs node
                                        // Handle the error appropriately
                                    }
                                });
                    }
                    else{
                        int positionToDelete = Integer.parseInt(list.get(position).getId())-1;
                        for (int i = positionToDelete; i < logs.size()-1; i++) {
                            String updatedLogEntry = logs.get(i+1);
                            userRef.child("Logs").child(filename).child(String.valueOf(i)).setValue(updatedLogEntry);
                        }
                        // Delete the last node
                        dataSnapshot.child(String.valueOf(logs.size()-1)).getRef().removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(ReadLog.this, "Entry Deleted ", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ReadLog.this, ReadLog.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // An error occurred while deleting the last entry
                                        // Handle the error appropriately
                                    }
                                });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error appropriately
            }
        });

    }
}
