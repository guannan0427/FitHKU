package com.example.guannan.fithku;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2  extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
        {

            private ListView lv;
            private Intent i;//保存点击按钮的key值,以区分是发布文字还是视频或是图片的layout

            private MyAdapter adapter;
            private NotesDB notesDB;
            private SQLiteDatabase dbReader;


           // private SQLiteDatabase dbWriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //addDB2();

        initView();
       // bindview();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        i=new Intent(this,momentsactivity.class);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(MainActivity2.this,momentsactivity.class);
                //startActivity(intent);
                i.putExtra("flag","3");
                startActivity(i);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
            /*
private void bindview() {
    rpTab = (RadioGroup) findViewById(R.id.rd_group);
    if (rpTab != null) {
        rpTab.setOnCheckedChangeListener(this);
    }
    rbCommon = (RadioButton)findViewById(R.id.rd_common);
    rbCollect = (RadioButton)findViewById(R.id.rd_collect);
    rbDishes = (RadioButton)findViewById(R.id.rd_dishes);
    if(rbCommon != null){
        rbCommon.setChecked(true);
    }
}

            public void hideAllFragment(FragmentTransaction transaction){
                if(fg1!=null){
                    transaction.hide(fg1);
                }
                if(fg2!=null){
                    transaction.hide(fg2);
                }
                if(fg3!=null){
                    transaction.hide(fg3);
                }
            }

            @Override
            public void onCheckedChanged(RadioGroup group, int checkid){
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                hideAllFragment(transaction);
                switch (checkid){
                    case R.id.rd_common:
                        if(fg1==null){
                            //String a = "第一个Fragment";
                            fg1 = new content2fragment();
                            transaction.add(R.id.fragment_container,fg1);
                        }else{
                            transaction.show(fg1);
                        }
                        break;
                    case R.id.rd_collect:
                        if(fg2==null){
                            fg2 = new content2fragment2();
                            //fg2 = new FirstFragment();
                            transaction.add(R.id.fragment_container,fg2);
                        }else{
                            transaction.show(fg2);
                        }
                        break;
                    case R.id.rd_dishes:
                        if(fg3==null){
                            fg3 = new content2fragment();
                            //fg3 = new FirstFragment();
                            transaction.add(R.id.fragment_container,fg3);
                        }else{
                            transaction.show(fg3);
                        }
                        break;
                }
                transaction.commit();
            }
*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        if(id==R.id.imgbtn){
            i.putExtra("flag","1");
            startActivity(i);

        }
        else if(id==R.id.videobtn){
            i.putExtra("flag","2");
            startActivity(i);

        }





        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent=new Intent(MainActivity2.this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

            public void initView(){
                lv=(ListView)findViewById(R.id.list);
                notesDB=new NotesDB(this);
                dbReader=notesDB.getReadableDatabase();
                //dbWriter=notesDB.getWritableDatabase();
                //addDB2();

            }
/*
            public void addDB2(){
                ContentValues cv=new ContentValues();
                cv.put(NotesDB.CONTENT,"hello");
                cv.put(NotesDB.TIME,getTime2());
               // dbWriter.insert(NotesDB.TABLE_NAME,null,cv);
            }

            public String getTime2(){
                SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date data=new Date();
                String str=format.format(data);
                return str;
            }
*/
            public void selectDB(){
                Cursor cursor=dbReader.query(NotesDB.TABLE_NAME,null,null,null,null,null,null);
                adapter=new MyAdapter(this,cursor);
                lv.setAdapter(adapter);
            }

          @Override
            public void onResume(){
                super.onResume();
                selectDB();
            }



}
