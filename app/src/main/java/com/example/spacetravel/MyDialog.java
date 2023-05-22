package com.example.spacetravel;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.spacetravel.R;
import com.example.spacetravel.activity.GameSurvivalLevel;
import com.example.spacetravel.activity.LevelsActivity;


public class MyDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        String title = "Окончание игры";
        String message = "Игра окончена!";
        String button1String = "Начать заново";
        String button2String = "Выйти в меню";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);

        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(getContext(),GameSurvivalLevel.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(getContext(), LevelsActivity.class);
                startActivity(intent);
            }
        });

        builder.setCancelable(true);

        return builder.create();
    }
}
