package com.example.hommieenglish;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;

public class AchievementActivity extends Activity {
    private ImageView imageViewGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        imageViewGif = findViewById(R.id.imageViewGif);

        Glide.with(this)
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
    }
}
