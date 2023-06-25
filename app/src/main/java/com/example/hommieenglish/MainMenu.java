package com.example.hommieenglish;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainMenu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        LinearLayout learnBtn = findViewById(R.id.learn_layout);
        LinearLayout achievementBtn = findViewById(R.id.achievement_layout);
        LinearLayout taskBtn = findViewById(R.id.task_layout);

        learnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LearningActivity.class);
                startActivity(intent);
            }
        });

        achievementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainMenu.this, "This is Achievement button !", Toast.LENGTH_SHORT).show();
            }
        });

        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainMenu.this, "This is Task button !", Toast.LENGTH_SHORT).show();
            }
        });
    }

}