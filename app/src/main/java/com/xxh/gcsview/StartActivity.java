package com.xxh.gcsview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void mpView(View view) {
        startActivity(new Intent(this,MpViewActivity.class));
    }

    public void canvasBasic(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void canvasBitmap(View view) {
        startActivity(new Intent(this,CanvasBitmapActivity.class));
    }
}
