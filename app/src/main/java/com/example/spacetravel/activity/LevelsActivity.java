package com.example.spacetravel.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.spacetravel.MyDialog;
import com.example.spacetravel.databinding.ActivityLevelsBinding;

public class LevelsActivity extends AppCompatActivity {
    private ActivityLevelsBinding binding;
    Intent intent;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        binding = ActivityLevelsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.button0.setOnClickListener(v -> {
            intent = new Intent(LevelsActivity.this, GameSurvivalLevel.class);
            startActivity(intent);
        });

        binding.btnmenu.setOnClickListener(v -> {
            intent = new Intent(LevelsActivity.this, MainActivity.class);
            startActivity(intent);
        });


    }

}