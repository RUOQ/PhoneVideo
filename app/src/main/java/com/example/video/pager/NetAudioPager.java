package com.example.video.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.video.base.BasePager;

public class NetAudioPager extends BasePager {
    public TextView mTextView;
    public NetAudioPager(Context context){
        super(context);
    }
    @Override
    public View initView() {
       mTextView = new TextView(mContext);
       mTextView.setText("我是网络音频");
       mTextView.setTextColor(Color.RED);
       mTextView.setTextSize(30);
       return mTextView;
    }

    @Override
    public void initData(){
        super.initData();
        System.out.println("网络音频数据初始化");
        mTextView.setText("我是网络音频");
    }
}
