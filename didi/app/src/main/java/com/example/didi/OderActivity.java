package com.example.didi;

        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import cn.bmob.v3.Bmob;
        import cn.bmob.v3.BmobUser;
        import cn.bmob.v3.exception.BmobException;
        import cn.bmob.v3.listener.SaveListener;
        import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by yf on 2020/3/24.
 */

public class OderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);


        //初始化 SDK信息
        Bmob.initialize(getApplicationContext(), "db4f9f061298384615f39dd2c7860e65");

        final EditText e_chufa = (EditText)findViewById(R.id.chufa);
        final EditText e_mudi = (EditText)findViewById(R.id.mudi);
        final EditText e_email = (EditText)findViewById(R.id.email);
        Button b_fabu = (Button)findViewById(R.id.id_login);

        //设置监听
        b_fabu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String chufa = e_chufa.getText().toString();  //获取文本信息
                String mudi = e_mudi.getText().toString();
                final  String email = e_email.getText().toString();
                Order  order = new Order();                             //实例化用户对象
                order.setChufa(chufa);
                order.setMudi(mudi);

                //实现向用户发送信息
                BmobUser.requestEmailVerify(email, new UpdateListener() {
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(getApplication(), "邮件已发送" ,Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplication(), "失败" +e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //用save方法实现发布订单（向后台插入数据）
                order.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(getApplication(), "发布成功" ,Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplication(), "失败" +e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });


    }
}

