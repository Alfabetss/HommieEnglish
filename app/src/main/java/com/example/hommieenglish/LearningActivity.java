package com.example.hommieenglish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class LearningActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        LinearLayout unit1Btn = findViewById(R.id.unit_1);
        LinearLayout unit2Btn = findViewById(R.id.unit_2);
        LinearLayout unit3Btn = findViewById(R.id.unit_3);
        LinearLayout unit4Btn = findViewById(R.id.unit_4);
        LinearLayout unit5Btn = findViewById(R.id.unit_5);
        LinearLayout unit6Btn = findViewById(R.id.unit_6);
        LinearLayout unit7Btn = findViewById(R.id.unit_7);

        unit1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LearningMenuActivity.class);
                startActivity(intent);
            }
        });

        unit2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LearningMenuActivity.class);
                startActivity(intent);
            }
        });

        unit3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LearningMenuActivity.class);
                startActivity(intent);
            }
        });

        unit4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LearningMenuActivity.class);
                startActivity(intent);
            }
        });

        unit5Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LearningMenuActivity.class);
                startActivity(intent);
            }
        });

        unit6Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LearningMenuActivity.class);
                startActivity(intent);
            }
        });

        unit7Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LearningMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
