package com.example.hommieenglish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class LearningMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_menu);

        Intent intent = getIntent();

        ImageButton learningBtn = (ImageButton) findViewById(R.id.learning_btn);
        ImageButton practiceBtn = (ImageButton) findViewById(R.id.practice_btn);

        learningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), VideoActivity.class);
                i.putExtra("video_url", intent.getStringExtra("video_url"));
                startActivity(i);
            }
        });

        practiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), TaskMultipleChoiceActivity.class);
                startActivity(intent);
            }
        });
    }
}