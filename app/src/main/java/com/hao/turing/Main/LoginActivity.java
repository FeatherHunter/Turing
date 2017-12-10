package com.hao.turing.Main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hao.turing.Bean.MyUser;
import com.hao.turing.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends Activity {

    private static final String TAG = LoginActivity.class.getName();
    TextView mLoginTextView;
    TextView mRegisterTextView;

    /**
     * 邮箱/密码
     */
    EditText mEmailEditText;
    EditText mPasswordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        //Bomb初始化
        Bmob.initialize(this, "f63b29c22665d5a71edfd31f3e25c71a");

        //控件初始化
        mLoginTextView = findViewById(R.id.login_textview);
        mRegisterTextView = findViewById(R.id.login_register_textview);
        mEmailEditText = findViewById(R.id.login_account_edittext);
        mPasswordEditText = findViewById(R.id.login_password_edittext);

        //登录/注册
        mLoginTextView.setOnClickListener(new LoginOrRegisterOnClickListener());
        mRegisterTextView.setOnClickListener(new LoginOrRegisterOnClickListener());

    }

    /**
     * @Description 登录或者注册按钮监听器
     * @author 王辰浩
     * @date 2017/12/14
     */
    private class LoginOrRegisterOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                //登录
                case R.id.login_textview:
                    if(TextUtils.isEmpty(mEmailEditText.getText().toString()) || TextUtils.isEmpty(mPasswordEditText.getText().toString())){
                        Toast.makeText(LoginActivity.this, "请输入邮箱/密码", Toast.LENGTH_SHORT).show();
                    }else{
                        MyUser user = new MyUser();
                        user.setUsername(mEmailEditText.getText().toString());
                        user.setPassword(mPasswordEditText.getText().toString());
                        user.login(new SaveListener<MyUser>() {

                            @Override
                            public void done(MyUser bmobUser, BmobException e) {
                                if(e==null){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(LoginActivity.this, "登陆失败：" +e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }



                    break;
                //注册
                case R.id.login_register_textview:
                    if(TextUtils.isEmpty(mEmailEditText.getText().toString()) || TextUtils.isEmpty(mPasswordEditText.getText().toString())){
                        Toast.makeText(LoginActivity.this, "请输入邮箱/密码", Toast.LENGTH_SHORT).show();
                    }else if(mPasswordEditText.getText().toString().length() < 6){
                        Toast.makeText(LoginActivity.this, "密码不能少于6位", Toast.LENGTH_SHORT).show();
                    }else if(mEmailEditText.getText().toString().length() < 6 || !mEmailEditText.getText().toString().contains("@")){
                        Toast.makeText(LoginActivity.this, "邮箱不合法", Toast.LENGTH_SHORT).show();
                    } else{
                        /*
                        * 获取邮箱/密码进行注册
                        * */
                        MyUser user = new MyUser();
                        user.setUsername(mEmailEditText.getText().toString());
                        user.setPassword(mPasswordEditText.getText().toString());
                        user.setEmail(mEmailEditText.getText().toString());
                        //注意：不能用save方法进行注册
                        user.signUp(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser s, BmobException e) {
                                if(e==null){
                                    Toast.makeText(LoginActivity.this, "注册成功:" +s.toString(), Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(LoginActivity.this, "注册失败:" +e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    break;
            }
        }
    }
}
