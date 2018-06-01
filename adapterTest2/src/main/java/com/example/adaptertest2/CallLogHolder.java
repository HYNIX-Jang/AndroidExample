package com.example.adaptertest2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CallLogHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {
    ImageView personImageView, dialerImageView;
    TextView nameView, dateView;
    CardView cardView;

    public CallLogHolder(View root) {
        super(root);
        cardView = root.findViewById(R.id.cardView);
        personImageView = root.findViewById(R.id.item_person);
        dialerImageView = root.findViewById(R.id.item_dialer);
        nameView = root.findViewById(R.id.item_name);
        dateView = root.findViewById(R.id.item_date);
    }

    @Override
    public void onClick(View v) {

    }
}
