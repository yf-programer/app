package com.example.didi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegistActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化 SDK信息
        Bmob.initialize(getApplicationContext(), "db4f9f061298384615f39dd2c7860e65");

        setContentView(R.layout.regist_layout);

        final EditText e_username = (EditText)findViewById(R.id.id_username);
        final EditText e_password = (EditText)findViewById(R.id.id_password);
        final EditText e_email = (EditText)findViewById(R.id.id_email);
        Button b_zhuce = (Button)findViewById(R.id.id_zhuce);

        //设置监听
        b_zhuce.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String username = e_username.getText().toString();  //获取文本信息
                String password = e_password.getText().toString();
                String email = e_email.getText().toString();
                User user = new User();                             //实例化用户对象
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);

                user.signUp(new SaveListener<BmobUser>(){
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null) {
                            User user = BmobUser.getCurrentUser(User.class);
                            Toast.makeText(getApplication(), "注册成功" ,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistActivity .this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplication(), "失败" +e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });

    }
}

