package com.xxh.gcsview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xxh.gcsview.pathtest.PathHeartView;

public class PathTestActivity extends AppCompatActivity {

    PathHeartView mPathHeartView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_test);
        mPathHeartView = (PathHeartView) findViewById(R.id.path_heart);

        mPathHeartView.startAnimation(10000);
    }
}
