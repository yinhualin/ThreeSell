package axhome.com.threesell.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import axhome.com.threesell.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PayActivity extends AppCompatActivity {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.iv_pay)
    ImageView ivPay;
    @InjectView(R.id.tv_paymoney)
    TextView tvPaymoney;
    @InjectView(R.id.tv_paycompany)
    TextView tvPaycompany;
    @InjectView(R.id.tv_pay_wechat)
    TextView tvPayWechat;
    @InjectView(R.id.tv_pay_ali)
    TextView tvPayAli;
    @InjectView(R.id.tv_pay_config)
    Button tvPayConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.inject(this);
        title.setText("支付订单");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @OnClick({R.id.iv_titleback, R.id.tv_pay_wechat, R.id.tv_pay_ali, R.id.tv_pay_config})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_titleback:
                finish();
                break;
            case R.id.tv_pay_wechat:
                break;
            case R.id.tv_pay_ali:
                break;
            case R.id.tv_pay_config:
                Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
