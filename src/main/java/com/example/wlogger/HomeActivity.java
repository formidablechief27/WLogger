package com.example.wlogger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wlogger.databinding.ActivityMainBinding;
import com.example.wlogger.databinding.HomeactivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    HomeactivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = HomeactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottom.setBackground(null);
        binding.bottom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.profile) {
                    // Use Intent to navigate to ProfileActivity (change ProfileActivity with your desired activity class)
                    Intent profileIntent = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(profileIntent);
                    return true;
                } else if (item.getItemId() == R.id.log) {
                    // Use Intent to navigate to SearchActivity (change SearchActivity with your desired activity class)
                    Intent searchIntent = new Intent(HomeActivity.this, LogActivity.class);
                    startActivity(searchIntent);
                    return true;
                }
                else if (item.getItemId() == R.id.search){
                    Intent searchIntent = new Intent(HomeActivity.this, SearchActivity.class);
                    startActivity(searchIntent);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
