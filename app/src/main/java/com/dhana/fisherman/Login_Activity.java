package com.dhana.fisherman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_Activity extends AppCompatActivity {
    EditText loginEmail,loginPass;
    Button login;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.edtEmaillogin);
        loginPass = findViewById(R.id.edtPasslogin);
        login = findViewById(R.id.btnLogin);
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = loginEmail.getText().toString();
                String userPassword = loginPass.getText().toString();
                if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword)){
                    Toast.makeText(Login_Activity.this, "Enter details", Toast.LENGTH_SHORT).show();
                }
                else {
                    userLogin(userEmail , userPassword);
                }
            }
        });
    }

    private void userLogin(String Email, String Password) {
        auth.signInWithEmailAndPassword(Email , Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login_Activity.this, "Login successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Login_Activity.this , User_Details.class));
                finish();
            }
        });
    }
}