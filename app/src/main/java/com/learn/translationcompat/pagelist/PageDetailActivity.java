package com.learn.translationcompat.pagelist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.learn.translationcompat.R;
import com.mrzk.transitioncontroller.controller.animationUtils.TransitionController;
import com.mrzk.transitioncontroller.controller.listener.TransitionCustomListener;

public class PageDetailActivity extends AppCompatActivity {

    private NestedScrollView nsv;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagedetail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().hide();
        nsv = (NestedScrollView) findViewById(R.id.nsv);
        nsv.setVisibility(View.INVISIBLE);
        ImageView iv_second = (ImageView) findViewById(R.id.iv_second);
        iv_second.setImageResource(R.drawable.list1);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

            mFloatingActionButton.setScaleX(0);
            mFloatingActionButton.setScaleY(0);
        TransitionController.getInstance().setEnterListener(new TransitionCustomListener() {
            @Override
            public void onTransitionStart(Animator animator) {
            }

            @Override
            public void onTransitionEnd(Animator animator) {
                getSupportActionBar().show();
                mFloatingActionButton.animate().setDuration(300).scaleX(1).scaleY(1);
                ObjectAnimator mAnimator = ObjectAnimator.ofFloat(nsv,"translationY",nsv.getHeight(),0);
//                Animator mAnimator = ViewAnimationCompatUtils.createCircularReveal( nsv, 0, 0, nsv.getWidth() / 2, nsv.getHeight());
                mAnimator.setDuration(300);
                mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                mAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                    @Override
                    public void onAnimationStart(Animator animation) {
                        nsv.setVisibility(View.VISIBLE);
                        super.onAnimationStart(animation);
                    }
                });
                mAnimator.start();
            }

            @Override
            public void onTransitionCancel(Animator animator) {
            }
        });
        TransitionController.getInstance().show(this,getIntent());
    }

    @Override
    public void onBackPressed() {

        mFloatingActionButton.animate().scaleX(0).scaleY(0).setListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                TransitionController.getInstance().exitActivity(PageDetailActivity.this);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
