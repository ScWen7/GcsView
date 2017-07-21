package com.xxh.gcsview.pathtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by 解晓辉 on 2017/7/7.
 * 作用：
 */

public class PathView1 extends View {

    public PathView1(Context context) {
        super(context);
    }

    public PathView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Paint circlePrint = new Paint();
        circlePrint.setDither(true);
        circlePrint.setAntiAlias(true);
        circlePrint.setColor(Color.RED);
        canvas.drawColor(Color.BLACK);

        Path rectPath = new Path();
        RectF rectF = new RectF(50, 50, 250, 200);
        rectPath.addRect(rectF, Path.Direction.CW);
//        canvas.drawPath(rectPath, circlePrint);


        Path circlePath = new Path();
        circlePath.addCircle(100, 100, 100, Path.Direction.CW);
//        circlePath.set(rectPath);   //该方法最好不用  受到硬件加速的影响
        canvas.drawPath(circlePath, circlePrint);


        Path mEndPath = new Path();
        mEndPath.addCircle(300, 300, 150, Path.Direction.CW);
        mEndPath.addCircle(380, 380, 150, Path.Direction.CW);
        //FillType   作用范围是  Path  所覆盖的矩形区域


//        mEndPath.setFillType(Path.FillType.WINDING);  //默认为 此模式，表现为 并集

//        mEndPath.setFillType(Path.FillType.EVEN_ODD);  //表示取   不相同的部分

//        mEndPath.setFillType(Path.FillType.INVERSE_WINDING);  //对并集取反
        mEndPath.setFillType(Path.FillType.INVERSE_EVEN_ODD);  //对 Even_oDD进行取反

        mEndPath.toggleInverseFillType();   //切换相反的填充模式


//        canvas.drawPath(mEndPath, circlePrint);


        RectF mComputeRect = new RectF();
        mEndPath = new Path();
        mEndPath.addCircle(380, 380, 150, Path.Direction.CW);
        mEndPath.addRect(new RectF(200, 300, 500, 500), Path.Direction.CW);
        mEndPath.computeBounds(mComputeRect, false);

        Log.e("TAG", "" + mComputeRect.left + "，" + mComputeRect.top + "," + mComputeRect.right + ","
                + mComputeRect.bottom);
        canvas.drawPath(mEndPath, circlePrint);
    }
}
