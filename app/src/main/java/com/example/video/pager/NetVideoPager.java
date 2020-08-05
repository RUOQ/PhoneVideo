package com.example.video.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.video.base.BasePager;




public class NetVideoPager extends BasePager {

    private TextView textView;


    public NetVideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(mContext);
//        textView.setText("我是网络视频");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        System.out.println("网络视频数据初始化了。。。");
        textView.setText("我是网络视频");
    }
}
