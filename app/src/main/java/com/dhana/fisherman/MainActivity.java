package com.dhana.fisherman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dhana.fisherman.Fragments.Home;
import com.dhana.fisherman.Fragments.Profile;
import com.dhana.fisherman.Fragments.Work;
import com.dhana.fisherman.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new Home());

//        bottomNavigationView = findViewById(R.id.mainBottomnavigation);
//        frameLayout = findViewById(R.id.mainFramelayout);
        binding.mainBottomnavigation.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.map:
                    replaceFragment(new Home());
                    break;
                case R.id.work:
                    replaceFragment(new Work());
                    break;
                case R.id.profile:
                    replaceFragment(new Profile());
                    break;
            }
            return true;
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFramelayout,fragment);
        fragmentTransaction.commit();
    }
}