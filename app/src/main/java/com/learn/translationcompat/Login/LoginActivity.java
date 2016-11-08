package com.learn.translationcompat.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;

import com.learn.translationcompat.R;
import com.mrzk.transitioncontroller.controller.animationUtils.TransitionController;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class LoginActivity extends AppCompatActivity{

    private EditText etUsername;
    private EditText etPassword;
    private CardView mCardView;
    private FloatingActionButton mFloatingActionButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        mCardView = (CardView) findViewById(R.id.cv);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fab:
                TransitionController.getInstance().startActivity(this,new Intent(this,RegisterActivity.class),mFloatingActionButton,R.id.fab);
                break;
            case R.id.bt_go:
                Intent intent = new Intent(this,LoginSuccessActivity.class);
                startActivity(intent);
                break;
            case R.id.forget_password:
                TransitionController.getInstance().startActivity(this,new Intent(this,ForgetPasswordActivity.class),mFloatingActionButton,R.id.fab);
                break;

        }
    }
}
