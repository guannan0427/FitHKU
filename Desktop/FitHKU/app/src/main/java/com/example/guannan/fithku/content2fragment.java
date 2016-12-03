package com.example.guannan.fithku;


import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.content.Context;



/**
 * Created by guannan on 1/12/2016.
 */

public class content2fragment extends Fragment
{
    String context;
    private ImageButton button1;
    private ListView lv;
    private MyAdapter adapter;
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;


    public content2fragment(){


    }



}
