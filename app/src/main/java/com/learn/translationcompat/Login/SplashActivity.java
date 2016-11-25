package com.learn.translationcompat.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.learn.translationcompat.R;
import com.learn.translationcompat.Utils.MyCountTimer;

/**
 * @author MinoZhang
 * @date 2016/11/25
 */

public class SplashActivity  extends AppCompatActivity{
    private Button start;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
}
