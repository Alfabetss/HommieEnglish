package com.example.hommieenglish;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

public class TaskMultipleChoiceActivity extends Activity {
    private String checkId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_multiple_choice_activity);

        RadioGroup rd = findViewById(R.id.multipleChoiceRadioGroup);
        rd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton1:
                        checkId = "A";
                    case R.id.radioButton2:
                        checkId = "B";
                    case R.id.radioButton3:
                        checkId = "C";
                    case R.id.radioButton4:
                        checkId = "D";
                }
            }
        });
    }
}
