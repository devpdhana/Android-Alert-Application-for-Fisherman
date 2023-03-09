package com.dhana.fisherman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register_Activity extends AppCompatActivity {
    EditText emailID,passWord;
    Button submit;
    TextView login;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailID = findViewById(R.id.edtEmail);
        passWord = findViewById(R.id.edtPassword);
        login = findViewById(R.id.txtLogin);
        submit = findViewById(R.id.btnSubmit);
        auth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = emailID.getText().toString();
                String userPass = passWord.getText().toString();

                if(TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPass)){
                    Toast.makeText(Register_Activity.this, "Please enter details", Toast.LENGTH_SHORT).show();
                }else {
                    userRegister(userEmail , userPass);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_Activity.this,Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void userRegister(String Email, String Pass) {
        auth.createUserWithEmailAndPassword(Email , Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(Register_Activity.this, "Register Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register_Activity.this , Login_Activity.class));
                    finish();
                } else {
                    Toast.makeText(Register_Activity.this, "Sorry regitration faild", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            startActivity(new Intent(Register_Activity.this , MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }
    }
}