package com.example.video;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class SystemVideoPlayer extends Activity {
    private VideoView mVideoView;
    private Uri mUri;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);

    }
}
