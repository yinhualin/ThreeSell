package axhome.com.threesell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import axhome.com.threesell.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ConfigOrderActivity extends AppCompatActivity {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.tv_username)
    TextView tvUsername;
    @InjectView(R.id.tv_useraddress)
    TextView tvUseraddress;
    @InjectView(R.id.iv_doorder_company)
    ImageView ivDoorderCompany;
    @InjectView(R.id.tv_doorder_companyname)
    TextView tvDoorderCompanyname;
    @InjectView(R.id.iv_goods)
    ImageView ivGoods;
    @InjectView(R.id.tv_goodsname)
    TextView tvGoodsname;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.tv_sellcount)
    TextView tvSellcount;
    @InjectView(R.id.tv_count)
    TextView tvCount;
    @InjectView(R.id.iv_doorder_jia)
    ImageView ivDoorderJia;
    @InjectView(R.id.tv_doorder_count)
    TextView tvDoorderCount;
    @InjectView(R.id.iv_doorder_jian)
    ImageView ivDoorderJian;
    @InjectView(R.id.et_doorder_say)
    EditText etDoorderSay;
    @InjectView(R.id.tv_doorder_allcount)
    TextView tvDoorderAllcount;
    @InjectView(R.id.tv_doorder_allprice)
    TextView tvDoorderAllprice;
    @InjectView(R.id.tv_doorder_allpay)
    TextView tvDoorderAllpay;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_order);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        title.setText("确认订单");

    }

    @OnClick({R.id.iv_titleback, R.id.ll_userinfo, R.id.iv_doorder_jia, R.id.iv_doorder_jian, R.id.btn_doorder_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_titleback:
                finish();
                break;
            case R.id.ll_userinfo:
                //跳转到选择地址界面
                startActivity(new Intent(this, AdressManagerActivity.class));
                break;
            case R.id.iv_doorder_jian:
                count = Integer.parseInt(tvDoorderCount.getText().toString());
                if (count == 1) {
                    Toast.makeText(this, "数量不能为0", Toast.LENGTH_SHORT).show();
                } else {
                    tvDoorderAllcount.setText("共" + (count - 1) + "件商品  小计:");
                    tvCount.setText("x" + (count - 1));
                    tvDoorderCount.setText(count - 1 + "");
                    tvDoorderAllprice.setText("¥ " + Double.parseDouble(tvPrice.getText().toString().replace("¥", "").trim()) * (count - 1));
                    tvDoorderAllpay.setText("¥ " + Double.parseDouble(tvPrice.getText().toString().replace("¥", "").trim()) * (count - 1));
                }
                break;
            case R.id.iv_doorder_jia:
                count = Integer.parseInt(tvDoorderCount.getText().toString());
                tvDoorderAllcount.setText("共" + (count + 1) + "件商品  小计:");
                tvCount.setText("x" + (count + 1));
                tvDoorderCount.setText(count + 1 + "");
                tvDoorderAllprice.setText("¥ " + Double.parseDouble(tvPrice.getText().toString().replace("¥", "").trim()) * (count + 1));
                tvDoorderAllpay.setText("¥ " + Double.parseDouble(tvPrice.getText().toString().replace("¥", "").trim()) * (count + 1));
                break;
            case R.id.btn_doorder_commit:
                //跳转到支付页面
                startActivity(new Intent(this, PayActivity.class));
                break;
        }
    }
}
