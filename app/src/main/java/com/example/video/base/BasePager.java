package com.example.video.base;

import android.content.Context;
import android.view.View;
/*
  1.本地音乐、本地视频、网络视频、网络音乐的基类，定义了基本抽象方法，
* */
public  abstract  class BasePager {
    public Context mContext;
    public View rootView;  //视图，有个子页面实例化的结果
    public  boolean isInitData = false;

    //构造方法
    public BasePager(Context mContext){
        this.mContext = mContext;
        rootView = initView();//调用的时候孩子的initView()
        isInitData =false;
    }

    public abstract  View initView();
    public void initData(){

    }

}
