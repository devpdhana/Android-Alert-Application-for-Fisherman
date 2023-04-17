package com.dhana.fisherman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.Timestamp;

//import java.sql.Timestamp;

public class Add_work extends AppCompatActivity {
    EditText fishName,fishDescrip;
    ImageButton saveWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);

        fishName = findViewById(R.id.edtFishName);
        fishDescrip = findViewById(R.id.edtFishDescrip);
        saveWork = findViewById(R.id.btnSave);

        saveWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveworkDetails();
            }
        });
    }

    void saveworkDetails(){
        String titleFishName = fishName.getText().toString();
        String descriptionFish = fishDescrip.getText().toString();
        if (titleFishName == null || titleFishName.isEmpty()){
            fishName.setError("Feild Reduired");
            return;
        }

        Savework savework = new Savework();
        savework.setTitle(titleFishName);
        savework.setContent(descriptionFish);
        savework.setTimestamp(Timestamp.now());
        saveWorkToFirebase(savework);
    }

    void saveWorkToFirebase (Savework savework){
        DocumentReference documentReference;
        documentReference = Utility.getCollectionReferenceForWork().document();

        documentReference.set(savework).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Utility.showToast(Add_work.this,"Work added Successfully");
                    finish();
                }else {
                    Utility.showToast(Add_work.this,"Failed while adding work");
                }
            }
        });
    }
}