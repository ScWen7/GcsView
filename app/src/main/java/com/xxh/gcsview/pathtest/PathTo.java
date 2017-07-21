package com.xxh.gcsview.pathtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 解晓辉 on 2017/7/7.
 * 作用：
 */

public class PathTo extends View{


    private Paint mPaint;

    public PathTo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);


        Path path1 = new Path();

        path1.moveTo(50,50); //将起点移动到   50,50坐标点
//        path1.lineTo(150,150);

        // 相对前面的点 x 往后移动 100 个像素，y 往下移动 100 个像素
        path1.rMoveTo(100, 100);
        path1.lineTo(400,400);


         //在 原来点的 点的基础上 x偏移100 像素 y偏移0像素
//        path1.rLineTo(100,0);


        RectF circleRect = new RectF(300,300,500,500);

        //参数说明RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo
       //  绘制曲线是从椭圆中截取一段 进行显示  oval 就是这个椭圆的区域
        // startAngle 开始的角度  sweepAngle 表示 圆弧划过的角度
        // forceMoveTo 为false  表示上一点会与圆弧的起点进行直线的连接   true 表示将 起点移动到圆弧的起点
        //为 true  会执行一次 moveTo 的操作

        path1.arcTo(circleRect,0,90,true);

        path1.close();  //将上一次 moveTo 的点与最后一个点连接起来

        canvas.drawPath(path1,mPaint);





    }
}
