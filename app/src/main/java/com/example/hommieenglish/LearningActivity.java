package com.example.hommieenglish;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hommieenglish.dao.LearningMaterialsDao;
import com.example.hommieenglish.db.HommieEnglish;
import com.example.hommieenglish.entity.LearningMaterials;
import com.example.hommieenglish.entity.Questions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class LearningActivity extends Activity {

    private HommieEnglish db;
    private LearningMaterialsDao learningMaterials;

    private List<LearningMaterials> materials;

    private int userId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        db = HommieEnglish.getInstance(this);
        learningMaterials = db.learningMaterialsDao();
        Intent intent = getIntent();
        userId = intent.getIntExtra("user_id", 0);
        CompletableFuture.supplyAsync(() -> learningMaterials.getAllMaterials())
                .thenAcceptAsync(dataList -> {
                   runOnUiThread(() -> {
                       LinearLayout linearLayout = findViewById(R.id.activity_learning);
                       for (int i = 0; i < dataList.size(); i++) {

                           LinearLayout materialList = new LinearLayout(this);
                           materialList.setOrientation(LinearLayout.HORIZONTAL);
                           materialList.setClickable(true);
                           materialList.setPadding(0,0,0,24);
                           materialList.setLayoutParams(QuestionActivity.matchParentWrapContent);

                           ImageView imageView = new ImageView(this);
                           String imageName = dataList.get(i).image_button_name;

                           int resID = getResources().getIdentifier(imageName, "drawable", getPackageName());
                           imageView.setImageResource(resID);
                           int widthInSp = 75;
                           int heightInSp = 75;

                           int widthInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, widthInSp, getResources().getDisplayMetrics());
                           int heightInPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, heightInSp, getResources().getDisplayMetrics());

                           LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(widthInPx, heightInPx);
                           imageView.setLayoutParams(layoutParams);
                           materialList.addView(imageView);

                           TextView textView = new TextView(this);
                           textView.setBackgroundResource(getResources().getIdentifier("background_color", "drawable", getPackageName()));
                           textView.setLayoutParams(QuestionActivity.matchParentMatchParent);
                           textView.setTextSize(30);
                           textView.setPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()), 0,0,0);
                           textView.setGravity(Gravity.CENTER_VERTICAL);
                           textView.setTextColor(Color.BLACK);
                           textView.setText(dataList.get(i).title);
                           materialList.addView(textView);

                           String videoUrl = dataList.get(i).videoUrl;
                           int unit = dataList.get(i).getUnit();
                           materialList.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                    Intent intent = new Intent(getBaseContext(), LearningMenuActivity.class);
                                    intent.putExtra("video_url", videoUrl);
                                    intent.putExtra("unit_id", unit);
                                    intent.putExtra("user_id", userId);
                                    startActivity(intent);
                               }
                           });

                           linearLayout.addView(materialList);
                       }
                   });
                });
    }
}
