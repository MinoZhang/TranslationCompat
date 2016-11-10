package com.learn.translationcompat.Login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.learn.translationcompat.R;
import com.mrzk.transitioncontroller.controller.animationUtils.TransitionController;
import com.mrzk.transitioncontroller.controller.animationUtils.ViewAnimationCompatUtils;
import com.mrzk.transitioncontroller.controller.listener.TransitionCustomListener;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class RegisterActivity extends AppCompatActivity {
    private FloatingActionButton mFloatingActionButton;
    private CardView mCardView;
    private Button btnEnsure;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mCardView = (CardView) findViewById(R.id.cv_add);
        btnEnsure = (Button) findViewById(R.id.bt_go);
        mCardView.setVisibility(View.INVISIBLE);
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
                Intent intent = new Intent(getApplicationContext(),PersonalInformationActivity.class);
                startActivity(intent);
            }
        });
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
