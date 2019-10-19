package ca.uottawa.cmcfa039.healthservicesapp;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.text.TextUtils;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.Task;


public class SplashActivity extends AppCompatActivity {

    private EditText welcomeEditText;
    private EditText signInEditText;
    private Button signOutButton;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();


        initalizeUI();

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    public void initalizeUI(){
        welcomeEditText = findViewById(R.id.welcomeText);
        signInEditText = findViewById(R.id.signInText);
        signOutButton = findViewById(R.id.signOutBtn);
    }


}


