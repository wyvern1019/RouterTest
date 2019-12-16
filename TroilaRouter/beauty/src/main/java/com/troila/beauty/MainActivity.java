package com.troila.beauty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.troila.router_annotation.Route;

@Route("/beauty/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
