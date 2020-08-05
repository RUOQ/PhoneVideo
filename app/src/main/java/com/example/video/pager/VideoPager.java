package com.example.video.pager;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.video.R;
import com.example.video.SystemVideoPlayer;
import com.example.video.base.BasePager;
import com.example.video.domain.MediaItem;
import com.example.video.utils.Utils;

import java.util.ArrayList;
public class VideoPager extends BasePager {

    private ListView lv_video_pager;
    private TextView tv_nomedia;
    private ProgressBar pb_loading;

    private ArrayList<MediaItem> mediaItems;
    private Utils utils;



    public VideoPager(Context context) {
        super(context);
        utils = new Utils();
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.video_pager,null);
        lv_video_pager = (ListView) view.findViewById(R.id.lv_video_pager);
        tv_nomedia = (TextView) view.findViewById(R.id.tv_nomedia);
        pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
        //设置点击事件
        lv_video_pager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MediaItem mediaItem = mediaItems.get(position);

//                Intent intent = new Intent();
//                intent.setDataAndType(Uri.parse(mediaItem.getData()),"video/*");
//                context.startActivity(intent);


                Intent intent = new Intent(mContext, SystemVideoPlayer.class);
                intent.setDataAndType(Uri.parse(mediaItem.getData()),"video/*");
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        System.out.println("本地视频数据初始化了。。。");
        getData();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //主线程
            if(mediaItems != null && mediaItems.size() >0){
                tv_nomedia.setVisibility(View.GONE);
                pb_loading.setVisibility(View.GONE);

                //设置适配器
                lv_video_pager.setAdapter(new VideoPagerAdapter());
            }else{
                tv_nomedia.setVisibility(View.VISIBLE);
                pb_loading.setVisibility(View.GONE);
            }

        }
    };

    class VideoPagerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mediaItems.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView ==null){
                convertView = View.inflate(mContext,R.layout.item_video_pager,null);
                viewHolder = new ViewHolder();
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.tv_duration = (TextView) convertView.findViewById(R.id.tv_duration);
                viewHolder.tv_size = (TextView) convertView.findViewById(R.id.tv_size);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            MediaItem mediaItem = mediaItems.get(position);
            viewHolder.tv_name.setText(mediaItem.getName());
            viewHolder.tv_size.setText(Formatter.formatFileSize(mContext, mediaItem.getSize()));
            viewHolder.tv_duration.setText(utils.stringForTime((int) mediaItem.getDuration()));
            return convertView;
        }
    }

    static class ViewHolder{
        TextView tv_name;
        TextView tv_duration;
        TextView tv_size;
    }

    private void getData() {


        new Thread(){
            @Override
            public void run() {
                super.run();

                mediaItems = new ArrayList<MediaItem>();
                ContentResolver contentResolver = mContext.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objects = {
                        MediaStore.Video.Media.DISPLAY_NAME,//在Sdcard显示的名称
                        MediaStore.Video.Media.DURATION,//视频的长度
                        MediaStore.Video.Media.SIZE,//视频文件大小
                        MediaStore.Video.Media.DATA//视频的绝对地址
                };
                Cursor cursor =  contentResolver.query(uri, objects, null, null, null);
                if(cursor != null){
                    while (cursor.moveToNext()){

                        MediaItem mediaItem = new MediaItem();
                        String name = cursor.getString(0);
                        mediaItem.setName(name);

                        long duration = cursor.getLong(1);
                        mediaItem.setDuration(duration);

                        long size = cursor.getLong(2);
                        mediaItem.setSize(size);

                        String data = cursor.getString(3);
                        mediaItem.setData(data);


                        //把视频添加到列表中
                        mediaItems.add(mediaItem);
                    }


                    cursor.close();;
                }


                handler.sendEmptyMessage(0);

            }
        }.start();
    }
}
