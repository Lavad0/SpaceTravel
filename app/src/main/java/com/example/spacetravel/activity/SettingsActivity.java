package com.example.spacetravel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.example.spacetravel.GameSurvivalView;
import com.example.spacetravel.R;
import com.example.spacetravel.databinding.ActivitySettingsBinding;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    private ActivitySettingsBinding binding;
    int k1 = 0;
    int k2 = 0;
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
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        GameSurvivalView gameSurvivalView = new GameSurvivalView(this);


        binding.btncontrol.setOnClickListener(v -> {
            k1 += 1;
            if (k1 % 2 == 0) binding.btncontrol.setText(R.string.control1);
            else binding.btncontrol.setText(R.string.control2);
        });
        binding.btnmenu.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
        });
        binding.btnmeteor1.setOnClickListener(v -> {
            binding.btnmeteor1.setBackgroundResource(R.color.purple_500);
            binding.btnmeteor2.setBackgroundResource(R.color.black);
            binding.btnmeteor3.setBackgroundResource(R.color.black);
            binding.btnmeteor4.setBackgroundResource(R.color.black);
            gameSurvivalView.setChangemeteor(1);
        });
        binding.btnmeteor2.setOnClickListener(v -> {
            binding.btnmeteor2.setBackgroundResource(R.color.purple_500);
            binding.btnmeteor1.setBackgroundResource(R.color.black);
            binding.btnmeteor3.setBackgroundResource(R.color.black);
            binding.btnmeteor4.setBackgroundResource(R.color.black);
            gameSurvivalView.setChangemeteor(2);
        });
        binding.btnmeteor3.setOnClickListener(v -> {
            binding.btnmeteor3.setBackgroundResource(R.color.purple_500);
            binding.btnmeteor1.setBackgroundResource(R.color.black);
            binding.btnmeteor2.setBackgroundResource(R.color.black);
            binding.btnmeteor4.setBackgroundResource(R.color.black);
            gameSurvivalView.setChangemeteor(3);
        });
        binding.btnmeteor4.setOnClickListener(v -> {
            binding.btnmeteor4.setBackgroundResource(R.color.purple_500);
            binding.btnmeteor1.setBackgroundResource(R.color.black);
            binding.btnmeteor2.setBackgroundResource(R.color.black);
            binding.btnmeteor3.setBackgroundResource(R.color.black);
            gameSurvivalView.setChangemeteor(4);
        });
        binding.btnship1.setOnClickListener(v -> {
            binding.btnship1.setBackgroundResource(R.color.purple_500);
            binding.btnship2.setBackgroundResource(R.color.black);
            gameSurvivalView.setChangeship(1);
        });
        binding.btnship2.setOnClickListener(v -> {
            binding.btnship2.setBackgroundResource(R.color.purple_500);
            binding.btnship1.setBackgroundResource(R.color.black);
            gameSurvivalView.setChangeship(2);
        });




    }


}