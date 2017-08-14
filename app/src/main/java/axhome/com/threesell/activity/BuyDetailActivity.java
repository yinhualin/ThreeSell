package axhome.com.threesell.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import axhome.com.threesell.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BuyDetailActivity extends AppCompatActivity {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.tv_goods)
    TextView tvGoods;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.tv_ordernumber)
    TextView tvOrdernumber;
    @InjectView(R.id.tv_time)
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_detail);
        ButterKnife.inject(this);
        title.setText("购买记录");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @OnClick(R.id.iv_titleback)
    public void onViewClicked() {
        finish();
    }
}
