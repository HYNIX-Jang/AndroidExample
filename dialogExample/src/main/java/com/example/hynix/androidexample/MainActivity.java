package com.example.hynix.androidexample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button alertBtn, listBtn, progressBtn, dateBtn, timeBtn, customBtn;
    AlertDialog alertDialog, listDialog, customDialog;
//    Date, Time,Progress는 이미 구현되어있음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        alertBtn = findViewById(R.id.alertBtn);
        listBtn = findViewById(R.id.listBtn);
        progressBtn = findViewById(R.id.progressDialog);
        dateBtn = findViewById(R.id.dateBtn);
        timeBtn = findViewById(R.id.timeBtn);
        customBtn = findViewById(R.id.customBtn);

        alertBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
        progressBtn.setOnClickListener(this);
        dateBtn.setOnClickListener(this);
        timeBtn.setOnClickListener(this);
        customBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == alertBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setTitle("알림");
            builder.setMessage("정말로 종료하시겠습니까?");
            builder.setPositiveButton("OK", dialogListener);
            builder.setNegativeButton("NO", null);

            alertDialog = builder.create();
            alertDialog.show();
        } else if (view == listBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("알람 벨소리");
            builder.setSingleChoiceItems(R.array.dialog_array, 0, dialogListener);
            builder.setPositiveButton("OK", null);
            builder.setNegativeButton("NO", null);

            listDialog = builder.create();
            listDialog.show();
        } else if (view == progressBtn) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Wait...");
            progressDialog.setMessage("서버 연동중입니다 잠시만 기다리세요.");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(true); //진행상태만 표시

            progressDialog.show();
        } else if (view == dateBtn) {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dateDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    showMessage(year + "년" + month + 1 + "월" + day + "일");
                }
            }, year, month, day);
            dateDialog.show();
        } else if (view == timeBtn) {
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR);
            int min = cal.get(Calendar.MINUTE);

            TimePickerDialog timeDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    showMessage(i + ":" + i1);
                }
            }, hour, min, true);
            timeDialog.show();
        } else if (view == customBtn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.dialog_layout, null);

            builder.setView(v);
            builder.setPositiveButton("OK", dialogListener);
            builder.setNegativeButton("NO", null);
            customDialog = builder.create();
            customDialog.show();
        }
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) { //dialog, i(어떤 아이템이 선택됐는지 각각의 정수타입으로 가져옴)
            if (dialogInterface == alertDialog && i == DialogInterface.BUTTON_POSITIVE) {
                showMessage("AlertDialog : OK button clicked");
            } else if (dialogInterface == alertDialog && i == DialogInterface.BUTTON_NEGATIVE) {
                showMessage("AlertDialog : NO button clicked");
            } else if (dialogInterface == listDialog) {
                String[] data = getResources().getStringArray(R.array.dialog_array);
                //Data 변수에 arrays.xml 의 array 가져옴
                showMessage(data[i] + "를 선택하셨습니다.");
            } else if (dialogInterface == customDialog && i == DialogInterface.BUTTON_POSITIVE) {
                showMessage("CustomDialog : OK button clicked");
            }
        }
    };
}
