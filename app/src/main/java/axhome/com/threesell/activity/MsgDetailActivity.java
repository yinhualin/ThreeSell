package axhome.com.threesell.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import axhome.com.threesell.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MsgDetailActivity extends AppCompatActivity {

    @InjectView(R.id.iv_titleback)
    ImageView ivTitleback;
    @InjectView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_msg_detail);
        ButterKnife.inject(this);
        title.setText("消息详情");
    }

    @OnClick(R.id.iv_titleback)
    public void onViewClicked() {
        finish();
    }
}
