package com.example.adaptertest2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CallRecyclerViewAdapter extends RecyclerView.Adapter<CallLogHolder> {
    Context context;
    int resId;
    ArrayList<CallLogVO> data;

    public CallRecyclerViewAdapter(Context context, int resId, ArrayList<CallLogVO> data) {
        this.context = context;
        this.resId = resId;
        this.data = data;
    }

    @NonNull
    @Override
    // 처음 뷰가 생성 될 때 호출되는 메소드: 새로운 뷰 생성
    public CallLogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
        CallLogHolder holder = new CallLogHolder(view);
        return holder;
    }

    @Override
    //listView의 getView를 담당하는 메소드
    public void onBindViewHolder(@NonNull CallLogHolder holder, int position) {
        CardView cardView = holder.cardView;

        ImageView personImageView = holder.personImageView;
        ImageView dialerImageView = holder.personImageView;
        TextView nameView = holder.nameView;
        TextView dateView = holder.dateView;

        final CallLogVO vo = data.get(position);
        nameView.setText(vo.name);
        dateView.setText(vo.date);
        if (vo.photo != null && vo.photo.equals("yes")) { //그림이 있으면
            personImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.hong, null));
        } else { // 없으면
            personImageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_person, null));
        }

        //전화번호가 있을 경우 dialer Image 를 클릭하면 전화걸기 화면이 나오게 함.
        if (vo.photo != null && !vo.photo.equals("")) {
            dialerImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);//암시적 Intent(명확하지 않을 때)
                    intent.setData(Uri.parse("tel:" + vo.phone));
                    context.startActivity(intent);
                }
            });
        }
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", vo.id); //DiplayCalling 액티비티에 보내는 정보
                dataBundle.putBoolean("isNew", false);
                Intent intent = new Intent(context, DisplayCallLogActivity.class);
                intent.putExtras(dataBundle);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void notifyDataSetChanged(ArrayList<CallLogVO> data) {
        this.data = data;
        notifyDataSetChanged(); //화면갱신
    }
}
