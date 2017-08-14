package axhome.com.threesell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import axhome.com.threesell.R;
import axhome.com.threesell.adapter.BuyRecordAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BuyRecordActivity extends AppCompatActivity {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.lv_buyrecord)
    ListView lvBuyrecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_record);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        title.setText("购买记录");
        ArrayList<String> list = new ArrayList<>();
        BuyRecordAdapter brAdapter = new BuyRecordAdapter(this, list);
        lvBuyrecord.setAdapter(brAdapter);
        lvBuyrecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(BuyRecordActivity.this, BuyDetailActivity.class));
            }
        });
    }

    @OnClick(R.id.iv_titleback)
    public void onViewClicked() {
        finish();
    }
}
