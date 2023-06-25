package com.example.hommieenglish;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {
    private VideoView videoView;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_learning_activity);

//        videoView = findViewById(R.id.video_view);

//        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_sample; // Ganti dengan sumber video Anda
//        Uri uri = Uri.parse(videoPath);
//        videoView.setVideoURI(uri);
//
//        MediaController mediaController = new MediaController(this);
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);

//        videoView.start();

        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String videoId = "e_04ZrNroTo"; // Ganti dengan ID video YouTube yang ingin Anda tampilkan
        String videoUrl = "https://www.youtube.com/embed/" + videoId;
        webView.loadUrl(videoUrl);
    }
}
