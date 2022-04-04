package com.example.lab7_lttbdd_th;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataUser extends SQLiteOpenHelper {
    public DataUser(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE user ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT , "+
                "name TEXT NOT NULL)";
        sqLiteDatabase.execSQL(sql);
    }

    public void addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",user.getName());
        db.insert("user",null,values);
    }

    public List<User> getAll()
    {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM USER";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst())
        {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                list.add(user);
            }while(cursor.moveToNext());
        }

        return list;
    }

    public void RemoveUser(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("USER","id =?",new String[]{String.valueOf(id)});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
