package com.xxh.gcsview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by 解晓辉 on 2017/6/20.
 * 作用：
 */

public class MyMpView extends View {
    private Paint mPaint;

    private int mWidth;
    private int mHeigth;
    private float mRadius;

    private List<PieData> mPieDatas;

    public MyMpView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        //防抖动 抗锯齿
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeigth = h;
        mRadius = Math.min(mWidth, mHeigth) * 0.8f / 2f;

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        if (mPieDatas == null) {
//            return;
//        }

        //移动画布到中心
        canvas.translate(mWidth / 2, mHeigth / 2);
        //
        RectF rectF = new RectF(-mRadius, -mRadius, mRadius, mRadius);


        //绘制外层圆形
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(1);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, mRadius, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.YELLOW);
        canvas.drawArc(rectF,-90,135,true,mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawArc(rectF,45,60,true,mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawArc(rectF,105,50,true,mPaint);







    }
}
