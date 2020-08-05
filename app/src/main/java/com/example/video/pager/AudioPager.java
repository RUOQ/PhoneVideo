package com.example.video.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.video.base.BasePager;

import org.w3c.dom.Text;

public class AudioPager extends BasePager {


    private TextView textView;
    public AudioPager(Context mcontext){
        super(mcontext);
    }
    @Override
    public View initView() {
       textView = new TextView(mContext);
       textView.setTextColor(Color.RED);
       textView.setGravity(Gravity.CENTER);
       textView.setTextSize(30);
       return textView;

    }
    @Override
    public void initData(){
        super.initData();
        System.out.println("本地音频数据初始化");
        textView.setText("我是本地音频");
    }
}
