package com.xxh.gcsview.pathtest;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import static android.content.ContentValues.TAG;

/**
 * Created by 解晓辉 on 2017/7/7.
 * 作用：
 */

public class PathHeartView extends View {
//


    private static final int PATH_WIDTH = 2;
    // 起始点
    private static final int[] START_POINT = new int[]{
            300, 270
    };
    // 爱心下端点
    private static final int[] BOTTOM_POINT = new int[]{
            300, 400
    };
    // 左侧控制点
    private static final int[] LEFT_CONTROL_POINT = new int[]{
            450, 200
    };
    // 右侧控制点
    private static final int[] RIGHT_CONTROL_POINT = new int[]{
            150, 200
    };
    private Paint mPaint;
    private Path mPath;

    private float[] currentPosition = new float[]{
            300, 270
    }; //当前位置的坐标
    private float[] tan = new float[2];  //当前位置点的Tan
    private PathMeasure mPathMeasure;


    public PathHeartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(PATH_WIDTH);
        mPaint.setColor(Color.RED);

        mPath = new Path();
        mPath.moveTo(START_POINT[0], START_POINT[1]);
        mPath.quadTo(LEFT_CONTROL_POINT[0], LEFT_CONTROL_POINT[1], BOTTOM_POINT[0], BOTTOM_POINT[1]);
        mPath.quadTo(RIGHT_CONTROL_POINT[0], RIGHT_CONTROL_POINT[1], START_POINT[0],
                START_POINT[1]);
        mPathMeasure = new PathMeasure(mPath, false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(mPath, mPaint);

        canvas.drawCircle(RIGHT_CONTROL_POINT[0], RIGHT_CONTROL_POINT[1], 5, mPaint);
        canvas.drawCircle(LEFT_CONTROL_POINT[0], LEFT_CONTROL_POINT[1], 5, mPaint);

        Log.e("TAG", "curerentX :" + currentPosition[0] + " currentY :" + currentPosition[1]);
        canvas.drawCircle(currentPosition[0], currentPosition[1], 5, mPaint);

    }

    public void startAnimation(long duration) {

        // 0 － getLength()
        ValueAnimator valueAnimator = ValueAnimator.ofFloat( 0,mPathMeasure.getLength());
        Log.i(TAG, "measure length = " + mPathMeasure.getLength());
        valueAnimator.setDuration(duration);
        // 减速插值器
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                // 获取当前点坐标封装到mCurrentPosition
                mPathMeasure.getPosTan(value, currentPosition, tan);
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
