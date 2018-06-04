package com.hynixlabs.jsonexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BookRecyclerAdapter extends RecyclerView.Adapter<ItemHolder> {
    BookItems bookItems;
    Context context;

    //    ArrayList<BookItems> datas;
    CardView cardView;
    ImageView list_img;
    TextView txt_title, txt_author, txt_price;

    public BookRecyclerAdapter(BookItems datas) {
        this.bookItems = datas;

    }

    @Override
    // 필수로 Generate 되어야 하는 메소드 1 : 새로운 뷰 생성
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }


    // 필수로 Generate 되어야 하는 메소드 2 : ListView의 getView 부분을 담당하는 메소드
    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        cardView = holder.cardView;
        list_img = holder.list_img;
        txt_title = holder.txt_title;
        txt_author = holder.txt_author;
        txt_price = holder.txt_price;

        txt_title.setText(Html.fromHtml(bookItems.getItems().get(position).getTitle()));
        txt_author.setText(Html.fromHtml(bookItems.getItems().get(position).getAuthor()));
        txt_price.setText(Html.fromHtml(bookItems.getItems().get(position).getPrice()));


        if(!bookItems.getItems().get(position).getImage().isEmpty()){
            Glide.with(context)
                    .load(bookItems.getItems().get(position).getImage().toString())
                    .into(list_img);

        }

    }
//
//        if ((!bookItems.getItems().get(position).getImage().isEmpty())) {
//            /*네트워크*/
//            ImageAsyncTask imageAsyncTask = new ImageAsyncTask();
//            imageAsyncTask.execute(bookItems.getItems().get(position).getImage());
//
//        }

//    private class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
//
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            Bitmap bitmap = null;
//            try {
//                URL url = new URL(strings[0]);
//                HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                bitmap = BitmapFactory.decodeStream(con.getInputStream());
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return bitmap;
//        }
//
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            list_img.setImageBitmap(bitmap);
//        }
//    }


    public BookRecyclerAdapter(Context context, BookItems datas) {
        this.context = context;
        this.bookItems = datas;
    }

    // 필수로 Generate 되어야 하는 메소드 3
    @Override
    public int getItemCount() {
        return bookItems.getItems().size();
    }


}
