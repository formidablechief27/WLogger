package com.example.wlogger;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class DatabaseDetails {
    public FirebaseAuth mAuth;
    public DatabaseReference mDatabase;
    public FirebaseUser user;
    String userId;

    public DatabaseDetails(FirebaseAuth mAuth, DatabaseReference mDatabase, FirebaseUser currentUser, String userId) {
        this.mAuth = mAuth;
        this.mDatabase = mDatabase;
        this.user = currentUser;
        this.userId = userId;
    }
}
