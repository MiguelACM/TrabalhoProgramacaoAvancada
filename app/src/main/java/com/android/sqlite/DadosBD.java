package com.android.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DadosBD {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DadosBD instance;


    private DadosBD(Context context) {
        this.openHelper = new DBHelper(context);
    }


    public static DadosBD getInstance(Context context) {
        if (instance == null) {
            instance = new DadosBD(context);
        }
        return instance;
    }


    public void open() {
        this.database = openHelper.getWritableDatabase();
    }


    public void close() {
        if (database != null) {
            this.database.close();
        }
    }


    public List<String> getDados(String query) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }



}
