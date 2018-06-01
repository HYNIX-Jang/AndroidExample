package com.hynixlabs.intentexample;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView resultView;
    private Button contactBtn, cameraDataBtn, voiceBtn, mapBtn, callBtn;
    private ImageView resultImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = findViewById(R.id.resultView);
        contactBtn = findViewById(R.id.btn_contact);
        cameraDataBtn = findViewById(R.id.btn_camera_data);
        resultImageView = findViewById(R.id.resultImageView);

        voiceBtn = findViewById(R.id.btn_voice);
        mapBtn = findViewById(R.id.btn_map);
        callBtn = findViewById(R.id.btn_call);

        contactBtn.setOnClickListener(this);
        cameraDataBtn.setOnClickListener(this);
        voiceBtn.setOnClickListener(this);
        mapBtn.setOnClickListener(this);
        callBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == contactBtn) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
            startActivityForResult(intent, 10);

        }

        if (v == cameraDataBtn) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 20);
        }

        if (v == voiceBtn) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "음성 인식 테스트");
            startActivityForResult(intent, 30);
        }

        if (v == mapBtn) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:39.0291382,125.7421118"));
            startActivity(intent);
        }
        if (v == callBtn) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) ==
                    PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:010-8377-1195"));
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                        200);
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 10 && resultCode == RESULT_OK) {
            String result = data.getDataString();
            resultView.setText(result);
        }

        if (requestCode == 20 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            resultImageView.setImageBitmap(bitmap);

        }
        if (requestCode == 30 && resultCode == RESULT_OK) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            resultView.setText(result.get(0));
        }

    }
}
