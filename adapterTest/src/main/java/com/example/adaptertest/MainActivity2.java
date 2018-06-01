package com.example.adaptertest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        ListView listView = (ListView) findViewById(R.id.custom_listview);
        ArrayList<DriveVO> data = new ArrayList<>(); //DB 데이터 저장

        //DB에 저장된 데이터를 data 변수에 넣기
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TB_DRIVE", null);
        while (cursor.moveToNext()) {
            DriveVO vo = new DriveVO();
            vo.title = cursor.getString(1); //title
            vo.date = cursor.getString(2); //date
            vo.type = cursor.getString(3); //type
            data.add(vo);
        }
        db.close();

        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_item, data);
        listView.setAdapter(adapter);
    }

}
