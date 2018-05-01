package com.example.a49876.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 49876 on 4/24/2018.
 */

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    private TextView email, password;
    private Button login, signup;
    public static FirebaseUser user;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        signup = findViewById(R.id.signup);
        signup.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }


    //sign in
    public void logIn(String email, String password) {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("Login", "signInWithEmail:success");
                            user = mAuth.getCurrentUser();
                            progressBar.setVisibility(View.INVISIBLE);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("login", "signInWithEmail:failure", task.getException());
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }

    //sign up
    public void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("Sign up", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LogInActivity.this, "Signup Success.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("Sign up", "createUserWithEmail:failure");
                            Toast.makeText(LogInActivity.this, "Signup Failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                        // ...
                    }
                });
    }




    private void updateUI(FirebaseUser user) {

        if (user != null) {
                Log.e("login","success");
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
        } else {
            Log.e("login","fail");
        }
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//    }


    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        progressBar.setVisibility(View.VISIBLE);
        switch(b.getId()) {
            case R.id.login:
            {
                logIn(email.getText().toString(),password.getText().toString());
                break;
            }
            case R.id.signup:
            {
                signUp(email.getText().toString(),password.getText().toString());
                break;
            }
        }
    }

}

