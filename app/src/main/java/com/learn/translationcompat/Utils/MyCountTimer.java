package com.learn.translationcompat.Utils;

import android.os.CountDownTimer;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

/**
 * @author MinoZhang
 * @date 2016/11/25
 */

public class MyCountTimer extends CountDownTimer {
    public static final int TIME_COUNT = 3100;//倒计时总时间为31S，时间防止从29s开始显示
    private TextView start;
    private String endStrRid;

    /**
     * @param millisInFuture    倒计时总时间（如30s,60S，120s等）
     * @param countDownInterval 渐变时间（每次倒计1s）
     * @param start             点击开始的按钮
     * @param endStrRid         倒计时结束后，按钮对应显示的文字
     */
    public MyCountTimer(long millisInFuture, long countDownInterval, TextView start, String endStrRid) {
        super(millisInFuture, countDownInterval);
        this.start = start;
        this.endStrRid = endStrRid;
    }
    public  MyCountTimer(TextView start,String endStrRid){
        super(TIME_COUNT,1000);
        this.start = start;
        this.endStrRid = endStrRid;
    }
    @Override
    public void onTick(long millisUntilFinished) {
        start.setEnabled(false);
        //每隔一秒修改一次UI
        start.setText(millisUntilFinished / 1000+"");

        // 设置透明度渐变动画
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        //设置动画持续时间
        alphaAnimation.setDuration(1000);
        start.startAnimation(alphaAnimation);

        // 设置缩放渐变动画
        final ScaleAnimation scaleAnimation =new ScaleAnimation(0.5f, 2f, 0.5f,2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        start.startAnimation(scaleAnimation);
    }

    @Override
    public void onFinish() {
        start.setText(endStrRid);
        start.setEnabled(true);
    }
}
