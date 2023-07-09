package com.example.hommieenglish;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TaskMultipleChoiceActivity extends Activity {
    private String checkId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_multiple_choice_activity);

        RadioGroup rd = findViewById(R.id.radioGroup);
        rd.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioButton1:
                        checkId = "A";
                        Toast.makeText(getApplicationContext(), "Ini Check ID "+ checkId, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioButton2:
                        checkId = "B";
                        Toast.makeText(getApplicationContext(), "Ini Check ID "+ checkId, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioButton3:
                        checkId = "C";
                        Toast.makeText(getApplicationContext(), "Ini Check ID "+ checkId, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.radioButton4:
                        checkId = "D";
                        Toast.makeText(getApplicationContext(), "Ini Check ID "+ checkId, Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
}
