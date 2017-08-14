package axhome.com.threesell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import axhome.com.threesell.R;
import axhome.com.threesell.config.NetConfig;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import okhttp3.Call;

import static android.R.attr.password;

public class RegisterNextActivity extends AppCompatActivity {

    @InjectView(R.id.tv_registernext_syb)
    TextView tvRegisternextSyb;
    @InjectView(R.id.et_registernext_pwd)
    EditText etRegisternextPwd;
    @InjectView(R.id.et_refisternext_surepwd)
    EditText etRefisternextSurepwd;
    @InjectView(R.id.btn_registernext_sure)
    Button btnRegisternextSure;
    private String phone;
    private String code;
    private String yqm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_next);
        ButterKnife.inject(this);
        phone = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");
        yqm = getIntent().getStringExtra("yqm");
        Log.e("aaa","---邀请码----->"+yqm);
        if(TextUtils.isEmpty(yqm)){
            yqm="";
        }

    }

    @OnClick({R.id.tv_registernext_syb, R.id.btn_registernext_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_registernext_syb:
                finish();
                break;
            case R.id.btn_registernext_sure:
               String pwd= etRegisternextPwd.getText().toString();
               String surepwd= etRefisternextSurepwd.getText().toString();
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(surepwd)){
                    Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                }else{
                    if(pwd.equals(surepwd)){
                        Log.e("aaa","---注册地址---->"+ NetConfig.SIGN_URL);
                        Log.e("aaa","---注册参数--mobile-->"+phone+"==captcha=="+code+"==password==="+pwd+"==pid=="+yqm);
                        OkHttpUtils.post().url(NetConfig.SIGN_URL)

                                .addParams("pid",yqm)
                                .addParams("mobile",phone)
                                .addParams("captcha",code)
                                .addParams("password",pwd)
                                .build()
                                .execute(new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Log.e("aaa","--注册返回---->"+response);

                                    }
                                });
                    }else{
                        Toast.makeText(this, "您两次输入的密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
        }
    }
}
