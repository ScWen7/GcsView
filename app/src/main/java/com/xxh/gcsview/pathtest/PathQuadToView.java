package com.xxh.gcsview.pathtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 解晓辉 on 2017/7/7.
 * 作用：
 */

public class PathQuadToView extends View {

    private Paint mHelpPaint;

    private float centerX;
    private float centerY;
    private PointF start = new PointF();  //初始点
    private PointF end = new PointF();  //结束点
    private PointF control = new PointF();  //控制点
    private Paint mQuadPaint;


    public PathQuadToView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mHelpPaint = new Paint();
        antiAliaPaint(mHelpPaint);
        mHelpPaint.setStyle(Paint.Style.STROKE);
        mHelpPaint.setStrokeWidth(3);
        mHelpPaint.setColor(Color.GRAY);

        mQuadPaint = new Paint();
        antiAliaPaint(mQuadPaint);
        mQuadPaint.setColor(Color.RED);
        mQuadPaint.setStyle(Paint.Style.STROKE);
        mQuadPaint.setStrokeWidth(3);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        // 初始化数据点和控制点的位置
        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        control.x = centerX;
        control.y = centerY - 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mHelpPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mHelpPaint);
        canvas.drawPoint(end.x, end.y, mHelpPaint);
        canvas.drawPoint(control.x, control.y, mHelpPaint);

        // 绘制辅助线
        mHelpPaint.setStrokeWidth(4);
        canvas.drawLine(start.x, start.y, control.x, control.y, mHelpPaint);
        canvas.drawLine(end.x, end.y, control.x, control.y, mHelpPaint);


        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.quadTo(control.x, control.y, end.x, end.y);
        canvas.drawPath(path, mQuadPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        control.x = event.getX();
        control.y = event.getY();
        postInvalidate();
        return true;
    }

    private void antiAliaPaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setDither(true);
    }
}
