package com.learn.translationcompat.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.learn.translationcompat.R;
import com.learn.translationcompat.database.DBHelper;
import com.mrzk.transitioncontroller.controller.animationUtils.TransitionController;
import com.mrzk.transitioncontroller.controller.animationUtils.ViewAnimationCompatUtils;
import com.mrzk.transitioncontroller.controller.listener.TransitionCustomListener;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class RegisterActivity extends AppCompatActivity{
    private FloatingActionButton mFloatingActionButton;
    private CardView mCardView;
    private Button btnEnsure;
    private EditText username,password,ensurePassword;
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        mCardView.setVisibility(View.INVISIBLE);
        mDBHelper = new DBHelper(this);
        TransitionController.getInstance().setEnterListener(new TransitionCustomListener() {
            @Override
            public void onTransitionStart(Animator animator) {

            }

            @Override
            public void onTransitionEnd(Animator animator) {
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Animator animator) {

            }
        });
        TransitionController.getInstance().show(this, getIntent());
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateRevealClose();
            }
        });
        btnEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(username.getText())){
                    if(!TextUtils.isEmpty(password.getText())&&!TextUtils.isEmpty(ensurePassword.getText())){
                        if(password.getText().toString().equals(ensurePassword.getText().toString())){
                            String userName = username.getText().toString();
                            String password = ensurePassword.getText().toString();
                            mDBHelper.insertOrUpdate(userName,password);
                            Intent intent = new Intent(getApplicationContext(),PersonalInformationActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(RegisterActivity.this, "两次密码输入不匹配，请重新输入", Toast.LENGTH_SHORT).show();
                            password.setText("");
                            ensurePassword.setText("");
                        }
                    }else {
                        Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mCardView = (CardView) findViewById(R.id.cv_add);
        btnEnsure = (Button) findViewById(R.id.bt_go);
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        ensurePassword = (EditText) findViewById(R.id.et_repeatpassword);
    }

    private void animateRevealClose() {
        final Animator mAnimator = ViewAnimationCompatUtils.createCircularReveal(mCardView, mCardView.getWidth() / 2, 0, mFloatingActionButton.getWidth() / 2, mCardView.getHeight());
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCardView.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                mFloatingActionButton.setImageResource(R.drawable.plus);
                TransitionController.getInstance().exitActivity(RegisterActivity.this);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    private void animateRevealShow() {
        Animator mAnimator = ViewAnimationCompatUtils.createCircularReveal(mCardView, mCardView.getWidth() / 2, 0, mFloatingActionButton.getWidth() / 2, mCardView.getHeight());
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                mCardView.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }
}
