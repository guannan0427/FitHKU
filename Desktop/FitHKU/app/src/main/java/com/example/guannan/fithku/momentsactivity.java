package com.example.guannan.fithku;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.VideoView;

import java.util.Date;

public class momentsactivity extends AppCompatActivity
   implements View.OnClickListener

{
    private Button send;
    private ImageView c_img;
    private VideoView c_video;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;
    private String val;//用来接收Mainactivity2中的flag值以确定显示的layout
    private File phoneFile;//图片的路径
           private EditText etContent;
           private View popupWindow;
           private PopupWindow mPopupWindow;
           private android.os.Handler mHandler = new android.os.Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmoments);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        val=getIntent().getStringExtra("flag");//接受flag的值
        c_img=(ImageView)findViewById(R.id.c_img);
        c_video=(VideoView)findViewById(R.id.c_video);
        initView();

        etContent= (EditText) findViewById(R.id.etContent);
        popupWindow=getLayoutInflater().inflate(R.layout.layout_popwindow,new LinearLayout(this),false);
        mPopupWindow = new PopupWindow(popupWindow, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        send=(Button)findViewById(R.id.send);



        send.setOnClickListener(this);
        etContent.setOnClickListener(this);


        notesDB=new NotesDB(this);
        dbWriter=notesDB.getWritableDatabase();
        /*

        etContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
                mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }, 500);
                mPopupWindow.showAtLocation(momentsactivity.this.findViewById(R.id.line), Gravity.BOTTOM, 0, 0);
            }
        });
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ImageButton back=(ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(momentsactivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });





    }




           @Override
           public boolean onCreateOptionsMenu(Menu menu) {
               // Inflate the menu; this adds items to the action bar if it is present.
               getMenuInflater().inflate(R.menu.main, menu);
               return true;
           }
           @Override
           public boolean onOptionsItemSelected(MenuItem item) {
               // Handle action bar item clicks here. The action bar will
               // automatically handle clicks on the Home/Up button, so long
               // as you specify a parent activity in AndroidManifest.xml.
               int id = item.getItemId();

               //noinspection SimplifiableIfStatement


               return super.onOptionsItemSelected(item);
           }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send:
                addDB();
                finish();

                break;

        }

    }

    //创建方法来添加数据
    public void addDB(){
        ContentValues cv=new ContentValues();
        cv.put(NotesDB.CONTENT,etContent.getText().toString());
        cv.put(NotesDB.TIME,getTime());
        cv.put(NotesDB.PATH,phoneFile+"");
        cv.put(NotesDB.VIDEO,2);
        dbWriter.insert(NotesDB.TABLE_NAME,null,cv);
    }

    //获取时间的方法
    public String getTime(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date data=new Date();
        String str=format.format(data);
        return str;
    }


    //初始化view的一个判断,以决定发布特定内容时候的layout,这里不管添加什么都要写东西,所以不判断edittext
    public void initView(){


        if(val.equals("1")){//添加的是图片
            c_img.setVisibility(View.VISIBLE);
            c_video.setVisibility(View.GONE);
            //直接跳转到系统相机
            Intent iimg=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //获取SD卡路径,因为数据库中存放的是路径,用绝对路径,getTime是用来命名的,名称不能相同,所以用时间最方便合适
            phoneFile=new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+ "/" +getTime() + ".jpg");
            iimg.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(phoneFile));
            //为了看到拍摄效果,这里选取待返回值的
            startActivityForResult(iimg,1);

        }
        if(val.equals("2")){//添加的是视频
            c_img.setVisibility(View.GONE);
            c_video.setVisibility(View.VISIBLE);

        }
        if(val.equals("3")){
            c_img.setVisibility(View.GONE);
            c_video.setVisibility(View.GONE);
        }
    }

    //在layout中显示照相
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1){
            Bitmap bitmap= BitmapFactory.decodeFile(phoneFile.getAbsolutePath());
            c_img.setImageBitmap(bitmap);
        }

    }

}

