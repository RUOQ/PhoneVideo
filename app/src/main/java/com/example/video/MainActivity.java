package com.example.video;


import android.os.Bundle;

import android.widget.RadioGroup;



import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.video.base.BasePager;
import com.example.video.pager.AudioPager;
import com.example.video.pager.NetAudioPager;
import com.example.video.pager.NetVideoPager;
import com.example.video.pager.VideoPager;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private int position;
    private RadioGroup rg_main;
    private ArrayList<BasePager> basePagers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);

        basePagers = new ArrayList<>();
        basePagers.add(new VideoPager(this));
        basePagers.add(new AudioPager(this));//本地音乐
        basePagers.add(new NetVideoPager(this));//网络视频
        basePagers.add(new NetAudioPager(this));//网络音乐

        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangedListener());
        rg_main.check(R.id.rb_audio);

    }

    class MyOnCheckedChangedListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                default://本地视频
                    position = 0;
                    break;
                case R.id.rb_audio:
                    position = 1;
                    break;
                case R.id.rb_net_video:
                    position = 2;
                    break;
                case R.id.rb_net_audio:
                    position = 3;
                    break;
            }
            setFragment();
        }
    }

    public void setFragment() {
        FragmentManager fm = getSupportFragmentManager();//得到fragmentManger
        FragmentTransaction ft = fm.beginTransaction();//开启事务
        ft.replace(R.id.fl_main, new ReplaceFragment(getBasePager()));


    }

    private BasePager getBasePager() {
        BasePager basePager = basePagers.get(position);
        if (basePager != null && !basePager.isInitData) {
            basePager.isInitData = true;
            basePager.initData();
        }
        return basePager;
    }
}

