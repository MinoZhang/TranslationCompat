package com.learn.translationcompat.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.learn.translationcompat.R;

/**
 * Created by Administrator on 2016/11/10 0010.
 */

public class PersonalInformationActivity extends AppCompatActivity {
    private ImageView accountImage;
    private EditText accountName,accountSignature;
    private RadioGroup mRadioGroup;
    private Button mButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnal);
        intView();
        initListener();
    }

    private void initListener() {
        accountImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PersonalInformationActivity.this, "正在研究中", Toast.LENGTH_SHORT).show();
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PersonalInformationActivity.this, "正在提交中", Toast.LENGTH_SHORT).show();
            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
    }

    private void intView() {
        accountImage = (ImageView) findViewById(R.id.account_image);
        accountName  = (EditText) findViewById(R.id.account_name);
        accountSignature = (EditText) findViewById(R.id.account_signature);
        mRadioGroup = (RadioGroup) findViewById(R.id.check_gender);
        mButton = (Button) findViewById(R.id.btn_ensure);
    }

}
