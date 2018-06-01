package com.example.contactdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView name, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        name = findViewById(R.id.result_name);
        phone = findViewById(R.id.result_phone);
        email = findViewById(R.id.result_email);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, phone, email " +
                "from TB_CONTACT order by id desc limit 1", null);
//      Cursor : 데이터 집합
        while (cursor.moveToNext()) {  //움직이면서 출력
            name.setText(cursor.getString(0));
            email.setText(cursor.getString(1));
            phone.setText(cursor.getString(2));

        }

        db.close();
    }
}
