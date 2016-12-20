package com.learn.translationcompat.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

import java.util.Random;

import static android.os.Looper.getMainLooper;

/**
 * Created by Administrator on 2016/12/20 0020.
 */

public class RandomCircle extends View {
    private Handler mHandler;
    private int mColor;
    private float x;
    private float y;
    private float radius;
    public RandomCircle(Context context) {
        super(context);
        mHandler = new Handler(getMainLooper());
        Thread mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                RandomCircle.this.invalidate();
                mHandler.postDelayed(this,100);
            }
        });
        mThread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        update();
        Paint mPaint = new Paint();
        mPaint.setColor(mColor);
        canvas.drawCircle(x,y,radius,mPaint);
    }

    private void update() {
        Random random = new Random();
        x = (float)(random.nextInt(200));
        y = (float)(random.nextInt(400));
        radius = (float)(10+random.nextInt(90));
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        mColor = Color.rgb(r,g,b);
    }

}

