package com.example.adaptertest2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DisplayCallLogActivity extends AppCompatActivity {
    // XML에 정의된 뷰 객체 변수 선언
    EditText editName, editDate, editPhone;
    Button btnSave, btnUpdate, btnDelete;

    // 인텐드 Extra에 포함된 data 저장 변수
    Boolean isNew;
    int itemId;

    //DB관련 변수들 선언
    DBHelper helper;
    SQLiteDatabase db;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_call_log);

        //뷰 객체 획득(findViewById)
        editName = findViewById(R.id.editName);
        editDate = findViewById(R.id.editDate);
        editPhone = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        helper = new DBHelper(this);
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            isNew = extra.getBoolean("isNew");
            if (isNew) {
                //새로 추가하는 경우
                btnUpdate.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE); //수정, 삭제버튼 안보이게 설정
            } else {
                //기존 아이템을 선택한 경우
                btnSave.setVisibility(View.INVISIBLE); //추가버튼 안보이게 설정
                itemId = extra.getInt("id");
                db = helper.getWritableDatabase();
                cursor = db.rawQuery("SELECT name, date, phone FROM tb_calllog WHERE id = ?",
                        new String[]{Integer.toString(itemId)});

                CallLogVO vo = new CallLogVO();
                while (cursor.moveToNext()) {
                    vo.name = cursor.getString(0);
                    vo.date = cursor.getString(1);
                    vo.phone = cursor.getString(2);
                }
                db.close();

                editName.setText(vo.name);
                editDate.setText(vo.date);
                editPhone.setText(vo.phone);
            }
        }
    }//End onCreate()


    public void save(View view) {
        String name = editName.getText().toString();
        String date = editDate.getText().toString();
        String phone = editPhone.getText().toString();

        db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO tb_calllog(name, photo, date, phone) values (?,?,?,?)",
                new String[]{name, "yes", date, phone});
        db.close();
        Toast.makeText(this, "DB에 연락처가 추가됨", Toast.LENGTH_LONG).show();

        finish(); //Activity 사라짐
    }

    public void update(View view) {
        String name = editName.getText().toString();
        String date = editDate.getText().toString();
        String phone = editPhone.getText().toString();

        db = helper.getWritableDatabase();

        ContentValues content = new ContentValues();
        content.put("name", name);
        content.put("date", date);
        content.put("phone", phone);

        db.update("tb_calllog", content, "id = ?", new String[]{Integer.toString(itemId)});
//      Contents 에 값을 넣고 UPDATE 함
        db.close();
        Toast.makeText(this, "DB에 연락처가 수정됨", Toast.LENGTH_LONG).show();
        finish(); //Activity 사라짐
    }

    public void delete(View view) {
        String name = editName.getText().toString();
        String date = editDate.getText().toString();
        String phone = editPhone.getText().toString();

        db = helper.getWritableDatabase();
        db.delete("tb_calllog", "id = ?", new String[]{Integer.toString(itemId)});
        db.close();
        Toast.makeText(this, "DB에 연락처가 삭제됨", Toast.LENGTH_LONG).show();
        finish(); //Activity 사라짐
    }
}