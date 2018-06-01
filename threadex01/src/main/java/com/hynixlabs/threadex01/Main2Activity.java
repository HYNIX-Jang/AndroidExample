package com.hynixlabs.threadex01;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Main2Activity extends AppCompatActivity {

    EditText ettUrl;
    Button btnDownload;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ettUrl = findViewById(R.id.et_url);
        btnDownload = findViewById(R.id.btn_download);
        imageView = findViewById(R.id.iv_image);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask().execute(ettUrl.getText().toString());
            }
        });
    }


    private Bitmap downloadUrl(String string) {
        Bitmap bitmap = null;
        InputStream inputStream = null;

        try {
            URL url = new URL(string);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (Exception e) {
            Log.d("Exception Downloading", e.toString());
        }
        return bitmap;
    }

    class DownloadTask extends AsyncTask<String, Void, Bitmap> {
        Bitmap bitmap = null;

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //이미지 다운로드가 끝나면 화면에 출력함
            imageView.setImageBitmap(bitmap);
            Toast.makeText(getApplicationContext(), "다운로드 성공", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            //EditText에서 입력받은 URL의 이미지를 다운로드함
            try {
                bitmap = downloadUrl(strings[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return bitmap;
        }
    }


}


