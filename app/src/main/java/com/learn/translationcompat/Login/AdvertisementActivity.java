package com.learn.translationcompat.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.learn.translationcompat.R;
import com.learn.translationcompat.adapter.AdvertiseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MinoZhang
 * @date 2016/11/25
 */

public class AdvertisementActivity extends AppCompatActivity{
    private ViewPager mViewPager;
    private List<Integer> mList = new ArrayList<>();
    private SharedPreferences mSharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mSharedPreferences = getSharedPreferences("ay", Context.MODE_PRIVATE);
        initData();
        initAdapter();
        setListener();
    }

    private void setListener() {
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            public float resX;
            public float downX;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        resX = downX - event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (mViewPager.getCurrentItem() == 2 && resX > 400) {
                            mSharedPreferences.edit().putBoolean("login", true).commit();
                            Intent intent = new Intent(AdvertisementActivity.this, SplashActivity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.activity_in_from_right, R.anim.activity_no_move);
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void initAdapter() {
        mViewPager.setAdapter(new AdvertiseAdapter(AdvertisementActivity.this,this,mList,mSharedPreferences));
    }

    private void initData() {
        mList.add(R.mipmap.adver_one);
        mList.add(R.mipmap.adver_two);
        mList.add(R.mipmap.adver_three);
        mList.add(R.mipmap.adver_four);
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }
}
