package com.dhana.fisherman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class User_Details extends AppCompatActivity {
    EditText userName,fatherName,adharNumber,mobileNumber,parentNumber,doorNo,streetName,villageName,pinCode;
    Button submitUser;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        userName = findViewById(R.id.edtadharName);
        fatherName = findViewById(R.id.edtfatherName);
        adharNumber = findViewById(R.id.edtAdharnumber);
        mobileNumber = findViewById(R.id.edtmobileNumer);
        parentNumber = findViewById(R.id.edtparentNumber);
        doorNo = findViewById(R.id.edtDoorno);
        streetName = findViewById(R.id.edtStreet);
        villageName = findViewById(R.id.edtTown);
        pinCode = findViewById(R.id.edtpinCode);
        submitUser = findViewById(R.id.btnUsersubmit);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        HashMap<String , Object> map = new HashMap<>();
//        map.put("Name" , "name");
//        map.put("Fname" , "fname");
//        map.put("Anum" , "adharNum");
//        map.put("Mnum" , "mobileNum");
//        map.put("Pnum" , "parentNum");
//        map.put("Dnum" , "DoorNum");
//        map.put("SName" , "streetNam");
//        map.put("Vname" , "villName");
//        map.put("Pcode" , "pCode");

//        String curUser = user.getUid();
//        DatabaseReference dbase = FirebaseDatabase.getInstance().getReference().child("Users").child(curUser);
//        dbase.updateChildren(map);
        submitUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString();
                String fname = fatherName.getText().toString();
                String adharNum = adharNumber.getText().toString();
                String mobileNum = mobileNumber.getText().toString();
                String parentNum = parentNumber.getText().toString();
                String DoorNum = doorNo.getText().toString();
                String streetNam = streetName.getText().toString();
                String villName = villageName.getText().toString();
                String pCode = pinCode.getText().toString();

                HashMap<String , Object> map = new HashMap<>();
                map.put("Name" , name);
                map.put("Fathername" , fname);
                map.put("Adharnum" , adharNum);
                map.put("Mobilenum" , mobileNum);
                map.put("Parentnum" , parentNum);
                map.put("Donum" , DoorNum);
                map.put("StreetName" , streetNam);
                map.put("Villagename" , villName);
                map.put("Pincode" , pCode);

                String curUser = user.getUid();
                DatabaseReference dbase = FirebaseDatabase.getInstance().getReference().child("Users").child(curUser);
                dbase.updateChildren(map);

                Toast.makeText(User_Details.this, "Details Submitted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(User_Details.this , MainActivity.class));
                finish();

            }
        });
    }
}