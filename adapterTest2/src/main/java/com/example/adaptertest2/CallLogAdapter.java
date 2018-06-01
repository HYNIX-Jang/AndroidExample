package com.example.adaptertest2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CallLogAdapter extends ArrayAdapter<CallLogVO> {
    Context context;
    int resId;
    ArrayList<CallLogVO> data;

    public CallLogAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CallLogVO> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resId = resource;
        this.data = objects;
    }

    @Override
    public int getCount() { //갯수 가져옴
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            //Item Layout을 메모리로 올리기 (Inflate)
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
            //Holder 이용
            CallLogHolder holder = new CallLogHolder(convertView);
            convertView.setTag(holder);

        }
        //Item Layout에 화면에 data 설정해서 출력
        CallLogHolder holder = (CallLogHolder) convertView.getTag();
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

        return convertView;
    }


}
