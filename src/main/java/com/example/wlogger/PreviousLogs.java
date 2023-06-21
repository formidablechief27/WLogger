package com.example.wlogger;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PreviousLogs extends AppCompatActivity implements listener{

    static String userId="";
    ArrayList<DateGet> list = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readprevlog);
        recyclerView = findViewById(R.id.loglist);
        fill();
    }

    public void show(){
        DateAdapter adapter = new DateAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void fill(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(userId);
        databaseReference.child("Logs").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    GenericTypeIndicator<Map<String, Object>> genericTypeIndicator = new GenericTypeIndicator<Map<String, Object>>() {};
                    Map<String, Object> logsData = dataSnapshot.getValue(genericTypeIndicator);

                    if (logsData != null) {
                        List<String> keyList = new ArrayList<>(logsData.keySet());
                        for(String s : keyList){
                            list.add(new DateGet(s));
                        }
                        show();
                    } else {
                        Toast.makeText(PreviousLogs.this, "No Logs Exist", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PreviousLogs.this, ProfileActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(PreviousLogs.this, "No Logs Exist", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PreviousLogs.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle potential errors
            }
        });
    }

    @Override
    public void OnItemClick(int position, int source) {
        ReadLogs.userId = PreviousLogs.userId;
        ReadLogs.date = list.get(position).getDate().trim();
        Intent intent = new Intent(PreviousLogs.this, ReadLogs.class);
        startActivity(intent);
    }
}
