package com.example.adaptertest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView arrayView, simpleView, cursorView;
    String[] arrayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayView = findViewById(R.id.main_listview_array);
        simpleView = findViewById(R.id.main_listview_simple);
        cursorView = findViewById(R.id.main_listview_cursor);

        arrayView.setOnItemClickListener(this);
        arrayData = getResources().getStringArray(R.array.location);
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayData);
        arrayView.setAdapter(adapter);

        //SimpleAdapter
        ArrayList<HashMap<String, String>> simpleData = new ArrayList<>();
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TB_DATA", null);

        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", cursor.getString(1));
            map.put("content", cursor.getString(2));
            simpleData.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, simpleData,
                android.R.layout.simple_list_item_2, new String[]{"name", "content"},
                new int[]{android.R.id.text1, android.R.id.text2});
        simpleView.setAdapter(simpleAdapter);


        //CursorAdapter 데이터베이스
        CursorAdapter cursorAdapter =
                new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor,
                        new String[]{"name", "content"}, new int[]{
                        android.R.id.text1, android.R.id.text2}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        cursorView.setAdapter(cursorAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, arrayData[position], Toast.LENGTH_LONG).show();
    }
}
