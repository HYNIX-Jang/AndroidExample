package com.example.memodb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hynix on 2018. 3. 23..
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, "MemoDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String memoSQL = "CREATE TABLE TB_MEMO(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "TITLE TEXT, "
                + "CONTENT TEXT)";
        db.execSQL(memoSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE TB_MEMO");
        onCreate(db);
    }
}
