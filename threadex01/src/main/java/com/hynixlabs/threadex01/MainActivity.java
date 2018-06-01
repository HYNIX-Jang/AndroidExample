package com.hynixlabs.threadex01;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    private ImageView startView, pauseView;
    private MyAsyncTask asyncTask;
    private boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        startView = findViewById(R.id.playBtn);
        pauseView = findViewById(R.id.pauseBtn);

        startView.setOnClickListener(this);
        pauseView.setOnClickListener(this);

        asyncTask = new MyAsyncTask();

    }

    @Override
    public void onClick(View v) {

        if (v == startView) {
            if (isFirst) {
                isFirst = true;
                asyncTask.execute();
                isFirst = false;
            } else {
                asyncTask.isRun = true;
            }


        } else if (v == pauseView) {
            asyncTask.isRun = false;
        }

    }

    class MyAsyncTask extends AsyncTask<Void, Integer, String> {
        private boolean isRun = true, loop = true;

        @Override
        protected String doInBackground(Void... voids) {
            int count = 10;
            while (loop) {
                SystemClock.sleep(1000); // 초
                if (isRun) {
                    count--;
                    publishProgress(count);
                    if (count == 0) loop = false;
                }
            }
            return "Finish";
        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText(String.valueOf(values[0]));
        }
    }
}
