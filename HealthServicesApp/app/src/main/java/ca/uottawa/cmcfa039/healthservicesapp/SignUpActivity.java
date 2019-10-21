package ca.uottawa.cmcfa039.healthservicesapp;

import android.content.Intent;
import android.widget.CheckBox;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.Task;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;


public class SignUpActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;
    private EditText emailEditText;
    private Button signupButton;
    private CheckBox patientBox;
    private CheckBox employeeBox;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        initializeUI();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpNewUser();
            }
        });
    }

    public void signUpNewUser() {

        final String firstName = firstNameEditText.getText().toString();
        final String lastName = lastNameEditText.getText().toString();
        final String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        final String confirmPassword = passwordConfirmEditText.getText().toString();

        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(getApplicationContext(), "Please enter First Name!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(getApplicationContext(), "Please enter Last Name!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Please confirm your password!", Toast.LENGTH_LONG).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short. Must be at least 6 characters.", Toast.LENGTH_LONG).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match. Try again!", Toast.LENGTH_LONG).show();
            return;
        }

        if (patientBox.isChecked() && employeeBox.isChecked()) {
            Toast.makeText(getApplicationContext(), "ERROR! Cannot be both a Patient AND an Employee!", Toast.LENGTH_LONG).show();
            return;
        }

        if(!(patientBox.isChecked() || employeeBox.isChecked())) {

            /*final Admin mAdmin = new Admin(firstName, lastName, password, email);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        mDatabase.getReference("/users/admins").child(mAuth.getCurrentUser().getUid()).setValue(mAdmin); //SECRET ADMIN GENERATION CODE, DISABLED UNTIL NEEDED
                        Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Auth Error", Toast.LENGTH_LONG).show();
                    }
                }
            });
*/
            Toast.makeText(getApplicationContext(), "ERROR! Must choose either Patient or Employee", Toast.LENGTH_LONG).show();
            return;
        }

        password = getSecurePassword(password);

        if (patientBox.isChecked()) {
            final Patient mPatient = new Patient(firstName, lastName, password, email);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        mDatabase.getReference("/users/patients").child(mAuth.getCurrentUser().getUid()).setValue(mPatient);
                        Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Auth Error", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        else {
            final Employee mEmployee = new Employee(firstName, lastName, password, email);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    mDatabase.getReference("/users/employees").child(mAuth.getCurrentUser().getUid()).setValue(mEmployee);
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Auth Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        }
    }

    public String getSecurePassword(String password){

        StringBuilder sbuild = new StringBuilder();;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));

            for(byte i : hash){
                sbuild.append(String.format("%02x", i));
            }
        }
        catch(NoSuchAlgorithmException e){

        }

        return sbuild.toString();

//        try {
//            md = MessageDigest.getInstance("SHA-256");
//            SecureRandom random = new SecureRandom();
//            byte[] salt = new byte[16];
//            random.nextByte(salt);
//        }

    }

    public void initializeUI(){
        firstNameEditText = findViewById(R.id.fnText);
        lastNameEditText = findViewById(R.id.lnText);
        emailEditText = findViewById(R.id.emText);
        passwordEditText = findViewById(R.id.pwText);
        passwordConfirmEditText = findViewById(R.id.pwcText);
        signupButton = findViewById(R.id.signupBtn);
        patientBox = findViewById(R.id.patientCheckBox);
        employeeBox = findViewById(R.id.employeeCheckBox);

    }

}
