package axhome.com.threesell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.youngkaaa.ycircleview.CircleView;

import axhome.com.threesell.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PersonDetailActivity extends AppCompatActivity {

    @InjectView(R.id.iv_head)
    CircleView ivHead;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_level)
    TextView tvLevel;
    @InjectView(R.id.tv_registertime)
    TextView tvRegistertime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_detail);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.back, R.id.btn_buyrecord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.btn_buyrecord:
                startActivity(new Intent(this, BuyRecordActivity.class));
                break;
        }
    }
}
