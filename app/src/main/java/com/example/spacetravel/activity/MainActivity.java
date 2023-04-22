package com.example.spacetravel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.spacetravel.GameView;
import com.example.spacetravel.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        //setContentView(binding.getRoot());
        setContentView(new GameView(this));
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        /*binding.startgamebtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameLevel.class);
            startActivity(intent);
        });*/

    }
}