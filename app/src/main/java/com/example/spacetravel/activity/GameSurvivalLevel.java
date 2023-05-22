package com.example.spacetravel.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.spacetravel.GameSurvivalView;
import com.example.spacetravel.MyDialog;
import com.example.spacetravel.R;


public class GameSurvivalLevel extends AppCompatActivity {
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
        super.onCreate(savedInstanceState);




        FrameLayout game = new FrameLayout(this);
        GameSurvivalView view = new GameSurvivalView(this);
        LinearLayout gameWidgets = new LinearLayout (this);

        Button PauseButton = new Button(this);
        Button MenuButton = new Button(this);



        PauseButton.setWidth(300);
        PauseButton.setText("Пауза");
        PauseButton.setOnClickListener(v -> {
            view.pausegame();
        });

        MenuButton.setWidth(300);
        MenuButton.setText("Меню");
        MenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameSurvivalLevel.this, LevelsActivity.class);
            startActivity(intent);
        });

        gameWidgets.addView(PauseButton);
        gameWidgets.addView(MenuButton);


        game.addView(view);
        game.addView(gameWidgets);


        setContentView(game);
        view.setManager(getSupportFragmentManager());

    }



}
