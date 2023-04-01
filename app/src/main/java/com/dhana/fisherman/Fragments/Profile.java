package com.dhana.fisherman.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.dhana.fisherman.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Fragment {
    TextView name,fatherNmae,mobileNum,parentNum,adharNum,doorNum,streetNam,villageNam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        name = view.findViewById(R.id.profileName);
        fatherNmae = view.findViewById(R.id.txtFnameVal);
        mobileNum = view.findViewById(R.id.txtMobilenumval);
        parentNum = view.findViewById(R.id.txtParentNumval);
        adharNum = view.findViewById(R.id.txtAdharnumberval);
        doorNum = view.findViewById(R.id.txtDoorval);
        streetNam = view.findViewById(R.id.txtStreetval);
        villageNam = view.findViewById(R.id.txtVillageval);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String nameofUser = user.getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        DatabaseReference userRef = databaseReference.child("Users").child(nameofUser);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nameofCurUser = snapshot.child("Name").getValue(String.class);
                String fnameofCurUser = snapshot.child("Fathername").getValue(String.class);
                String mobileofCurUser = snapshot.child("Mobilenum").getValue(String.class);
                String parentofCurUser = snapshot.child("Parentnum").getValue(String.class);
                String adharofCurUser = snapshot.child("Adharnum").getValue(String.class);
                String doorofCurUser = snapshot.child("Donum").getValue(String.class);
                String streetofCurUser = snapshot.child("StreetName").getValue(String.class);
                String villageofCurUser = snapshot.child("Villagename").getValue(String.class);
                name.setText(nameofCurUser);
                fatherNmae.setText(fnameofCurUser);
                mobileNum.setText(mobileofCurUser);
                parentNum.setText(parentofCurUser);
                adharNum.setText(adharofCurUser);
                doorNum.setText(doorofCurUser);
                streetNam.setText(streetofCurUser);
                villageNam.setText(villageofCurUser);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
