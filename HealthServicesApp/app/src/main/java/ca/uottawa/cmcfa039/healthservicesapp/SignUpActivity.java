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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.Task;


public class SignUpActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;
    private EditText emailEditText;
    private Button signupButton;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

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

        final String FIRST_NAME = firstNameEditText.getText().toString();
        final String LAST_NAME = lastNameEditText.getText().toString();
        final String email = emailEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        String confirmPassword = passwordConfirmEditText.getText().toString();

        if (TextUtils.isEmpty(FIRST_NAME)) {
            Toast.makeText(getApplicationContext(), "Please enter First Name!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(LAST_NAME)) {
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

        if(!password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match. Try again!", Toast.LENGTH_LONG).show();
            return;
        }

        final Patient mPatient = new Patient(FIRST_NAME, LAST_NAME, password, email);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {

                    mDatabase.getReference("/users/patients").child(mAuth.getCurrentUser().getUid()).setValue(mPatient);
                    Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Auth Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void initializeUI(){
        firstNameEditText = findViewById(R.id.fnText);
        lastNameEditText = findViewById(R.id.lnText);
        emailEditText = findViewById(R.id.emText);
        passwordEditText = findViewById(R.id.pwText);
        passwordConfirmEditText = findViewById(R.id.pwcText);
        signupButton = findViewById(R.id.signupBtn);
    }

}
