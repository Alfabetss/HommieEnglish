package com.example.hommieenglish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.example.hommieenglish.dao.AchievementDao;
import com.example.hommieenglish.db.HommieEnglish;
import com.example.hommieenglish.entity.Achievement;

import java.util.concurrent.CompletableFuture;

public class AchievementActivity extends Activity {
    private ImageView imageViewGif;
    private HommieEnglish db;
    private AchievementDao achievementDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        Intent intent = getIntent();
        db = HommieEnglish.getInstance(this);
        achievementDao = db.achievementDao();
        CompletableFuture.supplyAsync(() -> achievementDao.getByUserId(intent.getIntExtra("user_id", 0)))
                .thenAcceptAsync(listAchievement -> {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageViewGif = findViewById(R.id.imageViewGif);
                            Glide.with(getApplicationContext())
                                    .asGif()
                                    .load(R.raw.beginner)
                                    .into(new ImageViewTarget<GifDrawable>(imageViewGif) {
                                        @Override
                                        protected void setResource(GifDrawable resource) {
                                            if (resource == null) {
                                                Log.d("TAG", "resource is null");
                                                return;
                                            }
                                            imageViewGif.setImageDrawable(resource);
                                            resource.start();
                                        }
                                    });

                            double score = 0;
                            for (Achievement achievement : listAchievement) {
                                score += achievement.getScore();
                            }
                            TextView tv = findViewById(R.id.achievementTv);
                            StringBuilder sb = new StringBuilder();
                            sb.append("Congrats \n")
                                    .append(listAchievement.size())
                                    .append("/7 ")
                                    .append("Tasks Completed \n With a score of ")
                                    .append(score);
                            tv.setText(sb.toString());
                        }
                });
        });
    }
}
