package com.example.wlogger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

public class CardioLogger extends AppCompatActivity implements View.OnClickListener{

    ImageView images;
    TextView names;
    Button returnlist;
    Button readlog;
    String name;
    int image;
    Button logged;
    EditText text1, text2;

    static int count = 0;
    static String todayLog = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clogger);
        name = getIntent().getStringExtra("Name");
        image = getIntent().getIntExtra("Image", 0);
        String ppl = getIntent().getStringExtra("Ppl");
        images = findViewById(R.id.displayimage);
        names = findViewById(R.id.displayname);
        returnlist=findViewById(R.id.returnlist);
        readlog = findViewById(R.id.readlog);
        names.setText(name);
        images.setImageResource(image);
        logged = findViewById(R.id.logged);
        logged.setOnClickListener(this);
        returnlist.setOnClickListener(this);
        readlog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(R.id.returnlist == v.getId()){
            Intent intent = new Intent(CardioLogger.this, LogActivity.class);
            startActivity(intent);
        }
        if(R.id.readlog == v.getId()){
            Intent intent = new Intent(CardioLogger.this, ReadLog.class);
            startActivity(intent);
        }
        text1 = findViewById(R.id.weight);
        text2 = findViewById(R.id.reps);
        String weight = text1.getText().toString().trim();
        String reps = text2.getText().toString().trim();
        if(weight.equals("") || weight.equals(null)){
            Toast.makeText(getApplicationContext(), "Enter time", Toast.LENGTH_SHORT).show();
            return;
        }
        text1.setText("");
        text2.setText("");
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(MainActivity.userId);
        DatabaseReference logsRef = userRef.child("Logs").child(currentDate);

        logsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    count = (int) dataSnapshot.getChildrenCount();
                } else {
                    count = 0;
                }

                String logEntry = name + " " + weight + " " + reps + " " + (count + 1);

                logsRef.child(String.valueOf(count)).setValue(logEntry)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Logged", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Handle the error appropriately
                                    Toast.makeText(getApplicationContext(), "Failed to log", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error appropriately
            }
        });
//        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
//        String filename = currentDate;
//        int count = 0;
//        try {
//            FileInputStream fileInputStream = getApplicationContext().openFileInput(filename);
//            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            todayLog = "";
//            while(true){
//                String l = bufferedReader.readLine();
//                todayLog += l + "..";
//                if(l == null) {
//                    break;
//                }
//                ++count;
//            }
//        }
//        catch(FileNotFoundException e){
//            count = 0;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            Write(filename, name, weight, reps, (++count));
//            Toast.makeText(getApplicationContext(), "Logged", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(MainActivity.userId);
//        String finalword = name + " " + weight + " " + reps + " " + count + " ";
//        Toast.makeText(getApplicationContext(), "Logged", Toast.LENGTH_SHORT).show();
//        todayLog += finalword + "..";
//        userRef.child("Logs").child(filename).setValue(todayLog, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
//                if (databaseError == null) {
//                    // Data successfully written to the database
//                    // Proceed with any additional actions
//                } else {
//                    // An error occurred while writing to the database
//                    // Handle the error appropriately
//                }
//            }
//        });
    }
//    public void Write(String filename, String name, String weight, String reps, int count) throws IOException {
//        String finalword = name + " " + weight + " " + reps + " " + count + " ";
//        FileOutputStream fileOutputStream = getApplicationContext().openFileOutput(filename, Context.MODE_APPEND);
//        PrintWriter printWriter = new PrintWriter(fileOutputStream);
//        printWriter.println(finalword);
//        printWriter.close();
//    }
}
