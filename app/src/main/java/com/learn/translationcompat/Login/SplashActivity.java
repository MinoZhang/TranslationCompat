package com.learn.translationcompat.Login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.learn.translationcompat.R;
import com.learn.translationcompat.Utils.MyCountTimer;

import java.util.Random;

/**
 * @author MinoZhang
 * @date 2016/11/25
 */

public class SplashActivity  extends AppCompatActivity{
    private Button start;
    private int mColor1,mColor2;
    private Handler mHandler;
    private RelativeLayout mRelativeLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.colorfulLayout);
        mHandler = new Handler(getMainLooper());
        Thread mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                createColor();
                mRelativeLayout.setBackgroundColor(mColor1);
                start.setTextColor(mColor2);
                mHandler.postDelayed(this,1000);
            }
        });
        mThread.start();
        start = (Button) findViewById(R.id.btn_countTimer);
        MyCountTimer myCountTimer = new MyCountTimer(11000,1000,start,"开始");
        myCountTimer.start();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void createColor() {
        Random random = new Random();
        int r1 = random.nextInt(256);
        int g1 = random.nextInt(256);
        int b1 = random.nextInt(256);
        int r2 = random.nextInt(256);
        int g2 = random.nextInt(256);
        int b2 = random.nextInt(256);
        mColor1 = Color.rgb(r1,g1,b1);
        mColor2 = Color.rgb(r2,g2,b2);
    }


}
