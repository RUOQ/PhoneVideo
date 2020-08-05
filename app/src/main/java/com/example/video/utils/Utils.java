package com.example.video.utils;

import java.util.Formatter;
import java.util.Locale;

public class Utils {
    private StringBuilder mFormatBuilder;
    private Formatter mFormatter;

    public Utils(){
        //转换为字符串的时间

        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());

    }

    /* 将毫秒转换为1:20:30这里形式*/
    public String stringForTime(int timeMs){
        int totalSeconds = timeMs/1000;
        int seconds = totalSeconds%60;
        int minutes =(totalSeconds/60)%60;
        int hours = totalSeconds /3600;
        mFormatBuilder.setLength(0);
        if(hours>0){
            return mFormatter.format("%d:%02d:%02d",hours,minutes,seconds).toString();
        }else{
            return mFormatter.format("%02d:%02d",minutes,seconds).toString();
        }
    }
}
