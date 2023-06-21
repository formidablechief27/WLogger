package com.example.wlogger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    TextView name;
    TextView date;
    TextView days;
    ImageButton logout;
    Button history;
    long count =0;
    String username = "";
    String dat = "";
    TextView l1;
    TextView l2;
    TextView l3;
    TextView logs;
    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profilepage);
        name = findViewById(R.id.username);
        date = findViewById(R.id.date);
        logout = findViewById(R.id.logout);
        l1 = findViewById(R.id.logs);
        l2 = findViewById(R.id.followers);
        l3 = findViewById(R.id.following);
        filldetails();
        layout1 = findViewById(R.id.l1);
        layout2 = findViewById(R.id.f1);
        layout3 = findViewById(R.id.f2);
        layout1.setOnClickListener(this);
        layout2.setOnClickListener(this);
        layout3.setOnClickListener(this);
        logs = findViewById(R.id.logs2);
        logout.setOnClickListener(this);
        logs.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        filldetails();
    }

    public void filldetails(){
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(MainActivity.userId);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    username = dataSnapshot.child("username").getValue(String.class);
                    dat = dataSnapshot.child("Date").getValue(String.class);
                    name.setText("Hello, " + username);
                    date.setText("Member Since: " + dat);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error appropriately
            }
        });

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("users").child(MainActivity.userId).child("Logs");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = dataSnapshot.getChildrenCount();
                l1.setText(String.valueOf(count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                count = 0;
                // Handle the error appropriately
            }
        });
        databaseRef = FirebaseDatabase.getInstance().getReference().child("users").child(MainActivity.userId).child("Followers");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = dataSnapshot.getChildrenCount();
                l2.setText(String.valueOf(count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                count = 0;
                // Handle the error appropriately
            }
        });
        databaseRef = FirebaseDatabase.getInstance().getReference().child("users").child(MainActivity.userId).child("Following");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count = dataSnapshot.getChildrenCount();
                l3.setText(String.valueOf(count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                count = 0;
                // Handle the error appropriately
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.logout){
            File file = new File(getApplicationContext().getFilesDir(), "User Details");
            file.delete();
            Intent intent = new Intent(ProfileActivity.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        if(v.getId() == R.id.logs || v.getId() == R.id.logs2 || v.getId() == R.id.l1){
            PreviousLogs.userId = MainActivity.userId;
            Intent intent = new Intent(ProfileActivity.this, PreviousLogs.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.followers || v.getId() == R.id.followers2 || v.getId() == R.id.f1){
            FollowActivity.userId = MainActivity.userId;
            FollowActivity.node = "Followers";
            Intent intent = new Intent(ProfileActivity.this, FollowActivity.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.following || v.getId() == R.id.following2 || v.getId() == R.id.f2){
            FollowActivity.userId = MainActivity.userId;
            FollowActivity.node = "Following";
            Intent intent = new Intent(ProfileActivity.this, FollowActivity.class);
            startActivity(intent);
        }
    }
}
