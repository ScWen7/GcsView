package com.xxh.gcsview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.xxh.gcsview.pathtest.PathCubicToView;

public class PathCubicTestActivity extends AppCompatActivity {


    private PathCubicToView path_cubic;
    private RadioButton btn_control1;
    private RadioButton btn_control2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_cubic_test);
        path_cubic = (PathCubicToView) findViewById(R.id.path_cubic);
        btn_control1 = (RadioButton) findViewById(R.id.btn_control1);

        btn_control2 = (RadioButton) findViewById(R.id.btn_control2);

        btn_control1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    path_cubic.setMode(1);
                }
            }
        });

        btn_control2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    path_cubic.setMode(2);
                }
            }
        });


    }
}
