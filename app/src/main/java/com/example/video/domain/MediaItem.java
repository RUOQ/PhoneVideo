package com.example.video.domain;

/*
* 代表一个视频或者音频*/
public class MediaItem {
    private String name;
    private long duration;      //持续时间
    private long size;
    private String data;        //日期

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


   /* @Override
    public String toString(){
        return "MediaItem{"+
                "name ='" +name + '\'' +
                ",duration ="+duration+
                ",size = "+size +
                ",data ='"+data +'\''+
                '}';
    }
    */

    @Override
    public String toString() {
        return "MediaItem{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", data='" + data + '\'' +
                '}';
    }
}
