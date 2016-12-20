package com.learn.translationcompat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.learn.translationcompat.Login.AdvertisementActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btn_login:
                startActivity(new Intent(MainActivity.this, AdvertisementActivity.class));
                break;
        }
    }
}
