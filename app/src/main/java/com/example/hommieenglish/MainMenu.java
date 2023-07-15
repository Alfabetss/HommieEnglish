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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainMenu extends Activity {
    private Integer userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Intent i = getIntent();
        userId = i.getIntExtra("user_id", 0);
        ImageButton learnBtn = findViewById(R.id.learn_layout);
        ImageButton achievementBtn = findViewById(R.id.achievement_layout);
        ImageButton taskBtn = findViewById(R.id.task_layout);

        learnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LearningActivity.class);
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });

        achievementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AchievementActivity.class);
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });

        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TaskActivity.class);
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });
    }

}