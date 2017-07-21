package com.xxh.gcsview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 解晓辉 on 2017/6/20.
 * 作用：
 */

public class CanvasBasic1 extends View {

    private Paint mPaint;


    public CanvasBasic1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setDither(true); //防抖动
        mPaint.setAntiAlias(true); //抗锯齿


        mPaint.setStyle(Paint.Style.FILL); //填充
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStrokeWidth(20);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //绘制颜色
        canvas.drawColor(Color.GRAY);
        //绘制点
        canvas.drawPoint(10, 10, mPaint);
        //绘制一条线
        canvas.drawLine(50, 10, 100, 50, mPaint);
        //绘制矩形
        mPaint.setColor(Color.WHITE);
        RectF rectF = new RectF(100, 300, 300, 400);
        canvas.drawRect(rectF, mPaint);
        //绘制圆角矩形
        mPaint.setColor(Color.MAGENTA);
        RectF rectF1 = new RectF(100, 300, 300, 400);
        canvas.drawRoundRect(rectF1, 20, 20, mPaint); // rx  ry 确定了 椭圆的半径

        //绘制椭圆
        rectF1 = new RectF(100, 500, 300, 600);
        canvas.drawOval(rectF1, mPaint);

        //绘制矩形 及圆弧
        mPaint.setColor(Color.WHITE);
        rectF1 = new RectF(100, 700, 300, 800);
        canvas.drawRect(rectF1, mPaint);

        mPaint.setColor(Color.MAGENTA);
        canvas.drawArc(rectF1, 0, 90, false, mPaint); //useCenter 是否连接到椭圆圆心

        //关于画笔
        //Style  FILL  STROKE FILL_AND_STROKE

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(100, 1000, 100, mPaint); //   最终绘制出来的 图形的宽度为  radius*2 + stroke*2

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(100, 1200, 100, mPaint);  //填充，最终绘制出来的宽度为 radius *2


        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(100, 1400, 100, mPaint); // 填充+描边   最终绘制图形宽度为 radius*2 + stroke*2


        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);

//        rectF = new RectF(400, 100, 500, 200);
//        canvas.drawRect(rectF, mPaint);
//
//        //float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
////        float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
//        canvas.skew(1, 0);                       // 水平错切 <- 45度
//
//
//        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
//        canvas.drawRect(rectF, mPaint);


        canvas.drawCircle(600, 500, 200, mPaint);
        canvas.drawCircle(600, 500, 180, mPaint);

        canvas.save();
        canvas.translate(600, 500);

        for (int i = 0; i < 12; i++) {
            canvas.drawLine(180, 0, 200, 0, mPaint);
            canvas.rotate(30);
        }

        canvas.restore();
        canvas.drawLine(180,20,250,20,mPaint);


    }
}
