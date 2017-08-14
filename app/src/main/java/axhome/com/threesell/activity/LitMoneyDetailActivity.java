package axhome.com.threesell.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import axhome.com.threesell.R;
import axhome.com.threesell.adapter.MoneyDetailAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LitMoneyDetailActivity extends AppCompatActivity {

    @InjectView(R.id.iv_titleback)
    ImageView ivTitleback;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.lv_litmoney)
    ListView lvLitmoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lit_money_detail);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        title.setText("零钱明细");
        MoneyDetailAdapter mdAdapter = new MoneyDetailAdapter(this, new ArrayList<String>());
        lvLitmoney.setAdapter(mdAdapter);
    }

    @OnClick(R.id.iv_titleback)
    public void onViewClicked() {
        finish();
    }
}
