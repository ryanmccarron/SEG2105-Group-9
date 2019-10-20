package ca.uottawa.cmcfa039.healthservicesapp;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ValueEventListener;

import java.security.spec.ECField;


public class SplashActivity extends AppCompatActivity {

    private TextView welcomeEditText;
    private TextView signInEditText;
    private Button signOutButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser mUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUser = mAuth.getCurrentUser();


        initalizeUI();

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        if (mUser == null) {
            finish();
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

        } else {

            patientSplash(); //CHANGE THIS

        }
    }

    public void patientSplash() {
        mDatabase.child("/users/patients").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Patient currentPatient = dataSnapshot.getValue(Patient.class);

                String firstName = currentPatient.getFirstName();
                String type = "Patient";

                welcomeEditText.setText("Welcome " + firstName);
                signInEditText.setText("Your are a " + type);
                return;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void employeeSplash() {

        mDatabase.child("/users/employees").child(mUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Employee currentEmployee = dataSnapshot.getValue(Employee.class);

                String firstName = currentEmployee.getFirstName();
                String type = "Employee";

                welcomeEditText.setText("Welcome " + firstName);
                signInEditText.setText("Your are a " + type);
                return;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void initalizeUI(){
        welcomeEditText = findViewById(R.id.welcomeText);
        signInEditText = findViewById(R.id.signInText);
        signOutButton = findViewById(R.id.signOutBtn);
    }


}


