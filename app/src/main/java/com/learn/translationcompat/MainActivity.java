package com.learn.translationcompat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.learn.translationcompat.Login.LoginActivity;
import com.learn.translationcompat.pagelist.PageListActivity;

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
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.btn_list1:
                startActivity(new Intent(MainActivity.this, PageListActivity.class));
                break;
            case R.id.btn_list2:
                intent = new Intent(MainActivity.this, PageListActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            case R.id.btn_list3:
                intent = new Intent(MainActivity.this, PageListActivity.class);
                intent.putExtra("type",3);
                startActivity(intent);
                break;
        }
    }
}
