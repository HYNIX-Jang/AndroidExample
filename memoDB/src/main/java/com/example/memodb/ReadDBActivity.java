package com.example.memodb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ReadDBActivity extends AppCompatActivity {

    TextView titleVIew, contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_db);

        titleVIew = findViewById(R.id.read_title);
        contentView = findViewById(R.id.read_content);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select title, content from TB_MEMO order by id desc limit 1", null);
//      Cursor : 데이터 집합
        while (cursor.moveToNext()) {  //움직이면서 출력
            titleVIew.setText(cursor.getString(0));
            contentView.setText(cursor.getString(1));
        }

        db.close();

    }
}
