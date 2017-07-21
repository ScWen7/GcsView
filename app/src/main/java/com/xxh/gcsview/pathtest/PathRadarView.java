package com.xxh.gcsview.pathtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.xxh.gcsview.R;

/**
 * Created by 解晓辉 on 2017/7/7.
 * 作用：
 */

public class PathRadarView extends View {

    private int count = 6;

    private float angle = (float) (Math.PI * 2 / count);


    private float radius;                   //网格最大半径
    private int centerX;                  //中心X
    private int centerY;                  //中心Y
    private String[] titles = {"a", "b", "c", "d", "e", "f"};
    private float[] data = {100, 60, 60, 60, 100, 80, 10, 40}; //各维度分值
    private float maxValue = 100;             //数据最大值
    private Paint mLinePaint;
    private Paint mTextPaint;
    private Paint mCoverPaint;

    private int alphaGreen;


    public PathRadarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mLinePaint = new Paint();
        antiAliaPaint(mLinePaint);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setColor(Color.GRAY);

        mTextPaint = new Paint();
        antiAliaPaint(mTextPaint);
        mTextPaint.setColor(Color.GRAY);
        mTextPaint.setTextSize(30);


        mCoverPaint = new Paint();
        antiAliaPaint(mCoverPaint);
        mCoverPaint.setStrokeWidth(2);

        alphaGreen = context.getResources().getColor(R.color.alphaGreen);

    }

    private void antiAliaPaint(Paint paint) {
        paint.setAntiAlias(true);
        paint.setDither(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(h, w) / 2 * 0.9f;
        //中心坐标
        centerX = w / 2;
        centerY = h / 2;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPolygon(canvas);
        drawLines(canvas);
        drawTexts(canvas);
        drawCoverArea(canvas);
    }

    private void drawCoverArea(Canvas canvas) {
        Path path = new Path();
        mCoverPaint.setStyle(Paint.Style.FILL);
        mCoverPaint.setColor(Color.GREEN);


        for (int i = 0; i < count; i++) {
            float percent = data[i] / maxValue;


            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);

            if (i == 0) {
                path.moveTo(x, y);
            }
            path.lineTo(x, y);
            canvas.drawCircle(x, y, 10, mCoverPaint);
        }
        path.close();
        mCoverPaint.setColor(alphaGreen);
        mCoverPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, mCoverPaint);

    }

    private void drawTexts(Canvas canvas) {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        //测量字体的高度
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;

        for (int i = 0; i < count; i++) {

            float sweepAngle = angle * i;

            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(sweepAngle));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(sweepAngle));
            if (sweepAngle >= 0 && sweepAngle < Math.PI / 2) {
                canvas.drawText(titles[i], x, y, mTextPaint);
            } else if (sweepAngle >= Math.PI / 2 && sweepAngle < Math.PI) {
                float dis = mTextPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y, mTextPaint);
            } else if (sweepAngle >= Math.PI && sweepAngle < Math.PI / 2 * 3) {
                float dis = mTextPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x - dis, y + fontHeight / 2, mTextPaint);
            } else {
                float dis = mTextPaint.measureText(titles[i]);//文本长度
                canvas.drawText(titles[i], x, y + fontHeight / 2, mTextPaint);
            }

        }

    }

    /**
     * 绘制中心连接线
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {

        Path path = new Path();

        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mLinePaint);
        }
    }

    /**
     * 绘制正多边形
     *
     * @param canvas
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float r = radius / (count - 1);

        for (int i = 1; i < count; i++) {
            float curR = r * i;//当前半径

            path.reset();

            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mLinePaint);
        }
    }
}
