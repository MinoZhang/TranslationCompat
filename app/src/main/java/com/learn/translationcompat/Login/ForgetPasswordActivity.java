package com.learn.translationcompat.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.learn.translationcompat.R;
import com.mrzk.transitioncontroller.controller.animationUtils.TransitionController;
import com.mrzk.transitioncontroller.controller.animationUtils.ViewAnimationCompatUtils;
import com.mrzk.transitioncontroller.controller.listener.TransitionCustomListener;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText answer1, answer2;
    private Button ensure;
    private FloatingActionButton mFloatingActionButton;
    private CardView mCardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        answer1 = (EditText) findViewById(R.id.et_answer1);
        answer2 = (EditText) findViewById(R.id.et_answer2);
        mCardView = (CardView) findViewById(R.id.cv_add);
        mCardView.setVisibility(View.INVISIBLE);
        ensure = (Button) findViewById(R.id.bt_sure);
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
        TransitionController.getInstance().show(this,getIntent());

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });
        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ans1 = answer1.getText().toString();
                String ans2 = answer2.getText().toString();
                if (ans1.equals("")||ans2.equals("")){
                    Toast.makeText(ForgetPasswordActivity.this, "不回答就想知道密码？Too young！", Toast.LENGTH_SHORT).show();
                }else {
                    if(ans1.equals("11.9")&&ans2.equals("4")){
                        animateRevealClose();
                        Toast.makeText(ForgetPasswordActivity.this, "密码是IntoYou，真的是蠢", Toast.LENGTH_SHORT).show();
                    }else {

                        Toast.makeText(ForgetPasswordActivity.this, "你以为乱输你就能糊弄我，蒙混过关？", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_answer1:
                Toast.makeText(this, "想清楚再写,写错后果自负", Toast.LENGTH_SHORT).show();
                break;
            case R.id.et_answer2:
                Toast.makeText(this, "你的账号是这个吗？确定？", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationCompatUtils.createCircularReveal(mCardView, mCardView.getWidth()/2,0, mFloatingActionButton.getWidth() / 2, mCardView.getHeight());
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

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationCompatUtils.createCircularReveal(mCardView,mCardView.getWidth()/2,0, mCardView.getHeight(), mFloatingActionButton.getWidth() / 2);
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCardView.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                mFloatingActionButton.setImageResource(R.drawable.plus);
//                RegisterActivity.super.onBackPressed();
                TransitionController.getInstance().exitActivity(ForgetPasswordActivity.this);
            }

            @Override
            public void onAnimationStart(Animator animation) {
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