package axhome.com.threesell.activity;

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

public class ForgetPwdNextActivity extends AppCompatActivity {

    @InjectView(R.id.tv_forgetpwdnext_syb)
    TextView tvForgetpwdnextSyb;
    @InjectView(R.id.et_forgetpwdnext_pwd)
    EditText etForgetpwdnextPwd;
    @InjectView(R.id.et_forgetpwdnext_surepwd)
    EditText etForgetpwdnextSurepwd;
    @InjectView(R.id.btn_forgetpwdnext_sure)
    Button btnForgetpwdnextSure;
    private String phone;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd_next);
        ButterKnife.inject(this);
        phone = getIntent().getStringExtra("phone");
        code = getIntent().getStringExtra("code");
    }

    @OnClick({R.id.tv_forgetpwdnext_syb, R.id.btn_forgetpwdnext_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forgetpwdnext_syb:
                finish();
                break;
            case R.id.btn_forgetpwdnext_sure:
                String pwd= etForgetpwdnextPwd.getText().toString();
                String surepwd= etForgetpwdnextSurepwd.getText().toString();
                if(TextUtils.isEmpty(pwd)){
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(surepwd)){
                    Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                }else{
                    if(pwd.equals(surepwd)){
                        Log.e("aaa","---忘记密码地址---->"+ NetConfig.FORGETPWD_URL);
                        Log.e("aaa","---忘记密码参数--mobile-->"+phone+"==captcha=="+code+"==password==="+pwd);
                        OkHttpUtils.post().url(NetConfig.SIGN_URL)

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
                                        Log.e("aaa","--忘记密码返回---->"+response);

                                    }
                                });
                    }else{
                        Toast.makeText(this, "您两次输入的密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
