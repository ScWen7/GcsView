package com.xxh.gcsview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 解晓辉 on 2017/6/20.
 * 作用：
 */

public class CanvasBitmap extends View {
    private Paint mPaint;
    private Context mContext;
    private Bitmap mBitmap;
    private int mWidth;
    private int mHeight;

    public CanvasBitmap(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        mBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.image4);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawBitmap(mBitmap, 50, 50, mPaint);
        // 将画布坐标系移动到画布中央
        canvas.translate(mWidth/2,mHeight/2);

        // 指定图片绘制区域(左上角的四分之一)
        Rect src = new Rect(0,0,mBitmap.getWidth()/2,mBitmap.getHeight()/2);

        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(0,0,200,400);

        // 绘制图片
        canvas.drawBitmap(mBitmap,src,dst,null);

    }
}
