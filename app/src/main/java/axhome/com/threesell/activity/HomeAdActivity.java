package axhome.com.threesell.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import axhome.com.threesell.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HomeAdActivity extends AppCompatActivity {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.et_gift_name)
    EditText etGiftName;
    @InjectView(R.id.et_gift_phone)
    EditText etGiftPhone;
    @InjectView(R.id.et_gift_address)
    EditText etGiftAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ad);
        ButterKnife.inject(this);
        title.setText("领取礼品");
    }

    @OnClick({R.id.iv_titleback, R.id.btn_gift_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_titleback:
                finish();
                break;
            case R.id.btn_gift_submit:
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
