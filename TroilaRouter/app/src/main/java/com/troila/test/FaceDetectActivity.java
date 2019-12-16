package com.troila.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.troila.router_api.Router;

public class FaceDetectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detect);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Router.getInstance().startActivity("/beauty/main");
            }
        }, 3000);
    }
}
