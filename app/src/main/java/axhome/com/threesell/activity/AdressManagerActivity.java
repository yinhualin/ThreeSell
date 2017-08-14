package axhome.com.threesell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import axhome.com.threesell.R;
import axhome.com.threesell.adapter.AddressAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class AdressManagerActivity extends AppCompatActivity {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.lv_address)
    ListView lvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress_manager);
        ButterKnife.inject(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        title.setText("收货地址管理");
        initView();
    }

    private void initView() {
        AddressAdapter addressAdapter = new AddressAdapter(this, new String[5]);
        lvAddress.setAdapter(addressAdapter);

    }

    @OnClick({R.id.iv_titleback, R.id.btn_newaddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_titleback:
                finish();
                break;
            case R.id.btn_newaddress:
                startActivity(new Intent(this, NewAddressActivity.class));
                break;
        }
    }
}
