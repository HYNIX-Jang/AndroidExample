package com.example.adaptertest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private int resId;
    ArrayList<DriveVO> data;


    public CustomAdapter(@NonNull Context context, int resource, ArrayList<DriveVO> vo) {
        super(context, resource);
        this.context = context;
        this.resId = resource;
        this.data = vo;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Layout을 메모리로 올림
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(resId, null);
        //FindViewByID 하기
        ImageView type = (ImageView)convertView.findViewById(R.id.custom_item_type_image);
        TextView title = (TextView) convertView.findViewById(R.id.custom_item_title);
        TextView date = (TextView)convertView.findViewById(R.id.custom_item_date);
        ImageView menu = (ImageView)convertView.findViewById(R.id.custom_item_menu);

        //View에 데이터 설정

        final DriveVO vo = data.get(position);
        title.setText(vo.title);
        date.setText(vo.date);

        if (vo.type.equals("doc")) {
            type.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_type_doc, null));
        }
        if (vo.type.equals("file")) {
            type.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_type_file, null));
        }
        if (vo.type.equals("img")) {
            type.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_type_image, null));
        }

        //이벤트 처리
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, vo.title + "메뉴 클릭됨", Toast.LENGTH_LONG).show();
            }
        });

        return convertView;
    }


}

