package com.example.hommieenglish;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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
    private MediaPlayer mediaPlayer;
    private MediaPlayer buttonSound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        startBackgroundMusic();
        Intent i = getIntent();
        userId = i.getIntExtra("user_id", 0);
        ImageButton learnBtn = findViewById(R.id.learn_layout);
        ImageButton achievementBtn = findViewById(R.id.achievement_layout);
        ImageButton taskBtn = findViewById(R.id.task_layout);

        // Inisialisasi MediaPlayer dengan file audio di raw folder
        buttonSound = MediaPlayer.create(this, R.raw.button_sound);

        learnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playButtonSound();
                Intent intent = new Intent(getBaseContext(), LearningActivity.class);
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });

        achievementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playButtonSound();
                Intent intent = new Intent(getBaseContext(), AchievementActivity.class);
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });

        taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playButtonSound();
                Intent intent = new Intent(getBaseContext(), TaskActivity.class);
                intent.putExtra("user_id", userId);
                startActivity(intent);
            }
        });
    }

    private void startBackgroundMusic() {
        // Inisialisasi MediaPlayer dengan file audio di raw folder
        mediaPlayer = MediaPlayer.create(this, R.raw.backsound);

        // Mengatur agar backsound diulang-ulang (looping)
        mediaPlayer.setLooping(true);

        // Memulai memutar backsound
        mediaPlayer.start();
    }

    private void playButtonSound() {
        // Memulai memutar backsound tombol
        buttonSound.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Jika activity tidak lagi berada di depan (kehilangan fokus),
        // maka pause backsound agar tidak terus berlanjut di background
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Ketika activity kembali aktif setelah di pause, lanjutkan pemutaran backsound
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Bebaskan MediaPlayer saat activity dihancurkan
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (buttonSound != null) {
            buttonSound.release();
            buttonSound = null;
        }
    }

}