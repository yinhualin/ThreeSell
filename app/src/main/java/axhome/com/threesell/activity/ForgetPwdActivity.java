package axhome.com.threesell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import axhome.com.threesell.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ForgetPwdActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_forget_pwd);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.inject(this);
    }

    @OnClick({R.id.tv_login, R.id.btn_sendcode, R.id.btn_config})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btn_sendcode:
                i = 60;
                btnSendcode.setClickable(false);
                handler.postDelayed(runnable, DELYED); //每隔1s执行
                break;
            case R.id.btn_config:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
