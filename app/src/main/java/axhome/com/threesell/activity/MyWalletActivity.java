package axhome.com.threesell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import axhome.com.threesell.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyWalletActivity extends AppCompatActivity {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.tv_mywallet_mymoney)
    TextView tvMywalletMymoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.inject(this);
        title.setText("我的钱包");
    }

    @OnClick({R.id.iv_titleback, R.id.tv_mywallet_litnoney, R.id.btn_mywallet_cash})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_titleback:
                finish();
                break;
            case R.id.tv_mywallet_litnoney:
                //零钱明细
                startActivity(new Intent(this, LitMoneyDetailActivity.class));
                break;
            case R.id.btn_mywallet_cash:
                //提现
                Toast.makeText(this, "提现成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
