package com.learn.translationcompat.Login;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.learn.translationcompat.R;
import com.learn.translationcompat.Utils.UploadPicUtil;


/**
 * Created by Administrator on 2016/11/10 0010.
 */

public class PersonalInformationActivity extends AppCompatActivity {
    private Dialog chooseDialog;
    private ImageView accountImage;
    private EditText accountName,accountSignature;
    private RadioGroup mRadioGroup;
    private Button mButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnal);
        intView();
        showGuid();
        initListener();
    }

    private void showGuid() {


    }

    private void initListener() {
        accountImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDialog = new Dialog(PersonalInformationActivity.this,R.style.dialog);
                chooseDialog.setCanceledOnTouchOutside(false);
                chooseDialog.setCancelable(false);
                View dialog = LayoutInflater.from(PersonalInformationActivity.this).inflate(R.layout.choose_image_dialog,null);
                chooseDialog.setContentView(dialog);
                chooseDialog.show();
                ImageView makePhoto = (ImageView) dialog.findViewById(R.id.tv_makePhoto);
                ImageView goPhoto = (ImageView) dialog.findViewById(R.id.tv_goPhoto);
                makePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UploadPicUtil.openCamera(PersonalInformationActivity.this,1);
                    }
                });
                goPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UploadPicUtil.openPhotoAlbum(PersonalInformationActivity.this,2);
                    }
                });

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
                RadioButton mrb = (RadioButton) group.findViewById(checkedId);
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
