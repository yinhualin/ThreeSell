package axhome.com.threesell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import axhome.com.threesell.R;
import axhome.com.threesell.config.NetConfig;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

public class RegisterActivity extends AppCompatActivity {

    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.btn_sendcode)
    Button btnSendcode;
    @InjectView(R.id.et_code)
    EditText etCode;
    @InjectView(R.id.et_pwd)
    EditText etPwd;
    private int i = 60;
    private int DELYED = 1000;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, DELYED);
            btnSendcode.setText(Integer.toString(i) + "S后重试");
            i--;
            if (i == 0) {
                handler.removeCallbacks(runnable);
                btnSendcode.setText("发送验证码");
                btnSendcode.setClickable(true);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.tv_login, R.id.btn_sendcode, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_sendcode:
                i = 60;
                btnSendcode.setClickable(false);
                handler.postDelayed(runnable, DELYED); //每隔1s执行
                String phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else {
                Log.e("aaa","---注册验证码地址------>"+NetConfig.SIGN_SENDCODE_URL);
                Log.e("aaa","--注册验证码参数-----mobile-->"+phone);
                OkHttpUtils.post().url(NetConfig.SIGN_SENDCODE_URL)
                        .addParams("mobile",phone)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e("aaa","--注册发送验证码返回---->"+response);
                                try {
                                    JSONObject jo1=new JSONObject(response);
                                    String msg= (String) jo1.get("msg");
                                    int code=jo1.getInt("code");
                                    if(code==0){
                                       String queryInfo= (String) jo1.get("queryInfo");
                                        Log.e("aaa","--短信验证码-->"+queryInfo);
                                    }else{
                                        Toast.makeText(RegisterActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
        }
                break;
            case R.id.btn_register:
                String phone1 = etPhone.getText().toString().trim();
                String code = etCode.getText().toString().trim();
                String password = etPwd.getText().toString().trim();
                if (TextUtils.isEmpty(phone1)) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(code)){
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            }else {
                    Intent intent=new Intent(RegisterActivity.this,RegisterNextActivity.class);
                    intent.putExtra("phone",phone1);
                    intent.putExtra("code",code);
                    intent.putExtra("yqm",password);
                    startActivity(intent);

                }

                break;
        }
    }
}
