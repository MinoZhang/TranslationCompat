package com.learn.translationcompat.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.learn.translationcompat.R;
import com.learn.translationcompat.database.DBHelper;
import com.mrzk.transitioncontroller.controller.animationUtils.TransitionController;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private CardView mCardView;
    private DBHelper mDBHelper;
    private String[] userNames;
    private FloatingActionButton mFloatingActionButton;
    private Boolean isExist ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mDBHelper = new DBHelper(this);
        userNames = mDBHelper.queryAllUserName();
        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        mCardView = (CardView) findViewById(R.id.cv);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                TransitionController.getInstance().startActivity(this, new Intent(this, RegisterActivity.class), mFloatingActionButton, R.id.fab);
                break;
            case R.id.bt_go:
                isExist = false;
                if (TextUtils.isEmpty(username.getText())) {
                    Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                } else {
                    if (TextUtils.isEmpty(password.getText())) {
                        Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    } else {
                        here:for (int i = 0; i < userNames.length; i++) {
                            if (username.getText().toString().equals(userNames[i])) {
                                isExist = true;
                                break here;
                            }
                        }
                        if (isExist) {
                            String truePassword = mDBHelper.queryPasswordByName(username.getText().toString());
                            if (password.getText().toString().equals(truePassword)) {
                                Intent intent = new Intent(this, LoginSuccessActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(this, "密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "未注册用户", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


                break;
            case R.id.forget_password:
                TransitionController.getInstance().startActivity(this, new Intent(this, ForgetPasswordActivity.class), mFloatingActionButton, R.id.fab);
                break;

        }
    }
}
