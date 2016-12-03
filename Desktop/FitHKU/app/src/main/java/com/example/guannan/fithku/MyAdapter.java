package com.example.guannan.fithku;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by guannan on 2/12/2016.
 */

public class MyAdapter extends BaseAdapter {


    public Context context;//传递上下文
    private Cursor cursor;//从数据库中查询的内容
    private LinearLayout layout;//创建视图对象

    public MyAdapter(Context context, Cursor cursor){
        this.context=context;
        this.cursor=cursor;


    }

    //返回的是cursor的长度,cursor是一个游标,用cursor.getposition来返回
    @Override
    public int getCount(){
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        return cursor.getPosition();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);//加载视图的权限
        layout=(LinearLayout) inflater.inflate(R.layout.cell,null);//第一个参数是承载文字的页面
        //接下来获取上边这个布局文件中的每一个内容
        TextView contenttv=(TextView)layout.findViewById(R.id.list_content);
        TextView timetv=(TextView)layout.findViewById(R.id.list_time);
        ImageView imgiv=(ImageView)layout.findViewById(R.id.list_img);
        ImageView videoiv=(ImageView)layout.findViewById(R.id.list_video);

        //设置cursor的查询
        cursor.moveToPosition(position);
        //创建对象来保存cursor获取的内容
        String content=cursor.getString(cursor.getColumnIndex("content"));
        String time=cursor.getString(cursor.getColumnIndex("time"));
        //传递值以显示
        contenttv.setText(content);
        timetv.setText(time);

        return layout;
    }



}
