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

public class PathCubicToView extends View {


    private Paint mHelpPaint;

    private float centerX;
    private float centerY;
    private PointF start = new PointF();  //初始点
    private PointF end = new PointF();  //结束点
    private PointF control1 = new PointF();  //控制点
    private PointF control2 = new PointF();  //控制点
    private Paint mQuadPaint;

    private int mode = 1;

    public PathCubicToView(Context context, AttributeSet attrs) {
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

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        // 初始化数据点和控制点的位置
        start.x = centerX - 400;
        start.y = centerY;

        end.x = centerX + 400;
        end.y = centerY;

        control1.x = centerX - 200;
        control1.y = centerY - 200;

        control2.x = centerX + 200;
        control2.y = centerY + 200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mHelpPaint.setStrokeWidth(20);
        canvas.drawPoint(start.x, start.y, mHelpPaint);
        canvas.drawPoint(end.x, end.y, mHelpPaint);
        //绘制两个控制点
        canvas.drawPoint(control1.x, control1.y, mHelpPaint);
        canvas.drawPoint(control2.x, control2.y, mHelpPaint);

        // 绘制辅助线
        mHelpPaint.setStrokeWidth(4);
        Path linePath = new Path();
        linePath.moveTo(start.x, start.y);
        linePath.lineTo(control1.x, control1.y);
        linePath.lineTo(control2.x, control2.y);
        linePath.lineTo(end.x, end.y);
        canvas.drawPath(linePath, mHelpPaint);


        Path path = new Path();
        path.moveTo(start.x, start.y);
        path.cubicTo(control1.x, control1.y, control2.x, control2.y, end.x, end.y);

        canvas.drawPath(path, mQuadPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mode == 1) {
            control1.x = event.getX();
            control1.y = event.getY();
        } else {
            control2.x = event.getX();
            control2.y = event.getY();
        }

        postInvalidate();
        return true;
    }

    private void antiAliaPaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setDither(true);
    }
}
