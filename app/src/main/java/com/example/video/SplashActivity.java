package com.example.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
     private Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        },2000);
    }
    private boolean isStartMain = false;

    private void startMainActivity(){
        if(!isStartMain){
            isStartMain = true;
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            //关闭启动页
            finish();
        }
    }
}
