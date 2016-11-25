package com.learn.translationcompat.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.learn.translationcompat.Login.AdvertisementActivity;
import com.learn.translationcompat.Login.SplashActivity;
import com.learn.translationcompat.R;

import java.util.List;

/**
 * @author MinoZhang
 * @date 2016/11/25
 */

public class AdvertiseAdapter extends PagerAdapter{
    private Context context;
    private List<Integer> mList;
    private LayoutInflater mLayoutInflater;
    private AdvertisementActivity mAdvertisementActivity;
    private SharedPreferences mShare;
    private Button btn_start;
    private ImageView mImageView;
    public AdvertiseAdapter(Context context, AdvertisementActivity advertisementActivity, List<Integer> list, SharedPreferences mSharedPreferences){
        this.context = context;
        this.mList = list;
        mLayoutInflater = LayoutInflater.from(context);
        this.mAdvertisementActivity = advertisementActivity;
        this.mShare = mSharedPreferences;
    }
    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.advertise_item,container,false);
        btn_start = (Button) view.findViewById(R.id.btn_start);
        mImageView = (ImageView) view.findViewById(R.id.iv_item);
        mImageView.setImageResource(mList.get(position));
        if(position == (mList.size()-1)){
            btn_start.setVisibility(View.VISIBLE);
        }else {
            btn_start.setVisibility(View.INVISIBLE);
        }
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mShare.edit().putBoolean("login", true).commit();
                Intent intent = new Intent(context,SplashActivity.class);
                context.startActivity(intent);
                mAdvertisementActivity.finish();
                mAdvertisementActivity.overridePendingTransition(R.anim.activity_in_from_right,R.anim.activity_no_move);
            }
        });
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
