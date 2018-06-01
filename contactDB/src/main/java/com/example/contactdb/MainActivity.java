package com.example.contactdb;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edit_phone, edit_name, edit_email;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_email = findViewById(R.id.edit_email);
        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);

        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = edit_name.getText().toString();
        String email = edit_email.getText().toString();
        String phone = edit_phone.getText().toString();
        if (name.length() == 0) {
            Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            db.execSQL("INSERT INTO TB_CONTACT(name, phone, email) VALUES (?,?,?)",
                    new String[]{name, email, phone});
            db.close();

            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        }
    }
}
