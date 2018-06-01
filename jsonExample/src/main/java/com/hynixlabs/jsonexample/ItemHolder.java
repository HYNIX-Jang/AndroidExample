package com.hynixlabs.jsonexample;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemHolder extends RecyclerView.ViewHolder{
    public CardView cardView;
    public ImageView list_img;
    public TextView txt_title;
    public TextView txt_author;
    public TextView txt_price;


    public ItemHolder(View root) {
        super(root);
        cardView = root.findViewById(R.id.cardView);
        list_img = root.findViewById(R.id.list_img);
        txt_title = root.findViewById(R.id.txt_title);
        txt_author = root.findViewById(R.id.txt_author);
        txt_price = root.findViewById(R.id.txt_price);
    }
}
