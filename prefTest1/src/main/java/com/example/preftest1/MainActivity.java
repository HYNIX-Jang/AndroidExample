package com.example.preftest1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefs";
    EditText value;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value = findViewById(R.id.editName);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);  // 모드명, 모드
        name = settings.getString("Name", "기본");
        value.setText(name);

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);  // 모드명, 모드
        SharedPreferences.Editor editor = settings.edit();
        name = value.getText().toString();
        editor.putString("Name", name);
        editor.apply(); // 저장, 반영
    }
}


