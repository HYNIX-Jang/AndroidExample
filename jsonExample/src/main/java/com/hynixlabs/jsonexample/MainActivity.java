package com.hynixlabs.jsonexample;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText editSearch;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("네이버 도서검색-AsyncTask");

        btn = findViewById(R.id.button1);
        editSearch = findViewById(R.id.editSearch);
        recyclerView = findViewById(R.id.main_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 네트워크 자료 파싱
                String key = editSearch.getText().toString();
                String apiURL = "https://openapi.naver.com/v1/search/book?query=" + key + "&display=30&start=1"; // json 결과
                BookAsyncTask bookAsyncTask = new BookAsyncTask();
                bookAsyncTask.execute(apiURL); //doInBackground()에 값을 넘겨줌
            }
        });

    }

    private class BookAsyncTask extends AsyncTask<String, Void, String> {
        String clientId = "sg3ym0bsgTSLA9wj4W5P"; //애플리케이션 클라이언트 아이디값";
        String clientSecret = "KI1v3AuquJ"; //애플리케이션 클라이언트 시크릿값";

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            /*Naver Example Code: https://developers.naver.com/docs/search/blog/ */
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) { // 정상 호출
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {  // 에러 발생
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                result = response.toString();
            } catch (Exception e) {
                e.printStackTrace();

            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            ArrayList<BookItems> booklist = parseToJson(s);
            BookRecyclerAdapter bookRecyclerAdapter = new BookRecyclerAdapter(booklist);
            recyclerView.setAdapter(bookRecyclerAdapter);
        }

        private ArrayList<BookItems> parseToJson(String s) {
            ArrayList<BookItems> booklist = new ArrayList<>();
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(s);
                JSONArray items = jsonObject.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject o = items.getJSONObject(i);
                    BookItems bookItems = new BookItems();
                    bookItems.setTitle(o.getString("title"));
                    bookItems.setImage(o.getString("image"));
                    bookItems.setAuthor(o.getString("author"));
                    bookItems.setPrice(o.getString("price"));

                    booklist.add(bookItems);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return booklist;
        }
    }


}
