package com.example.adaptertest2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean callPermission;

    RecyclerView recyclerView;
    Button btnAdd;
    CallRecyclerViewAdapter adapter;

    DBHelper helper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CALL_PHONE Permission 요청
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            callPermission = true;
        }

        if (!callPermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 200);
        }

        //RecyclerView, Button 객체정보 가져오기(findViewById)
        recyclerView = findViewById(R.id.main_list);
        recyclerView.setHasFixedSize(true); // 사이즈고정

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);


        ArrayList<CallLogVO> data = getData();

        //CallLogAdapter 설정(추가정보: Item Layout, CallLogVO 데이터)
        // ListView에 어댑터를 설정
        adapter = new CallRecyclerViewAdapter(this, R.layout.main_list_item, data);
        recyclerView.setAdapter(adapter);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);
                dataBundle.putBoolean("isNew", true);
                Intent intent = new Intent(getApplicationContext(), DisplayCallLogActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<CallLogVO> data = getData();
        adapter.notifyDataSetChanged(data); //
    }

    private ArrayList<CallLogVO> getData() {
        ArrayList<CallLogVO> data = new ArrayList<CallLogVO>();
        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        cursor = db.rawQuery("SELECT id,name, photo, date, phone FROM tb_calllog", null);
        while (cursor.moveToNext()) {
            CallLogVO vo = new CallLogVO();
            vo.id = cursor.getInt(0);
            vo.name = cursor.getString(1);
            vo.photo = cursor.getString(2);
            vo.phone = cursor.getString(3);
            data.add(vo);
        }
        db.close();
        return data;
    }
}