package com.example.librarylighthouse;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.librarylighthouse.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                LoadFragment(new HomeFragment());
            } else if (itemId == R.id.nav_search) {
                LoadFragment(new NotificationFragment());
            } else if (itemId == R.id.nav_user) {
                LoadFragment(new ProfileFragment());
            }else {
                return false;
            }
            return true;
        });

        if (savedInstanceState == null) {
            binding.bottomNavigationView.setSelectedItemId(R.id.nav_home);
        } else {
            binding.bottomNavigationView.setSelectedItemId(savedInstanceState.getInt("selectedItemId"));
        }
    }

    private void LoadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}

