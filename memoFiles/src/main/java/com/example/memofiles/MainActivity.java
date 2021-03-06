package com.example.memofiles;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText contentView;
    Button btn;

    boolean fileReadPermission;
    boolean fileWritePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentView = findViewById(R.id.content);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED)
            fileReadPermission = true;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED)
            fileWritePermission = true;

        if (!fileReadPermission || !fileWritePermission) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) fileReadPermission = true;
        if (grantResults[1] == PackageManager.PERMISSION_GRANTED) fileWritePermission = true;
    }

    @Override
    public void onClick(View view) {
        String content = contentView.getText().toString();
        if (fileReadPermission && fileWritePermission) {
            FileWriter writer;
            try {
                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myapp";
                File dir = new File(dirPath);

                if (!dir.exists()) {
                    dir.mkdir();
                }

                File file = new File(dir + "/myfile.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }

                writer = new FileWriter(file, true);
                writer.write(content);
                writer.flush(); //refresh
                writer.close();

                Intent intent = new Intent(this, ReadFileActivity.class); //다른 엑티비티 호출
                startActivity(intent);//호출

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
