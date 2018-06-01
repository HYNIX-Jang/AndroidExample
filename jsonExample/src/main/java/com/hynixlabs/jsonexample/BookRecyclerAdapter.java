package com.hynixlabs.jsonexample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class BookRecyclerAdapter extends RecyclerView.Adapter<ItemHolder> {
    ArrayList<BookItems> datas;
    CardView cardView;
    ImageView list_img;
    TextView txt_title, txt_author, txt_price;

    public BookRecyclerAdapter(ArrayList<BookItems> datas) {
        this.datas = datas;
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
       


    }
        // 필수로 Generate 되어야 하는 메소드 3
        @Override
        public int getItemCount() {
        	return datas.size();  
        }


   
}
