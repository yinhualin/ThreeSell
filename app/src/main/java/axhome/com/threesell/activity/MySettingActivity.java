package axhome.com.threesell.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import axhome.com.threesell.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MySettingActivity extends AppCompatActivity {

    @InjectView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
        ButterKnife.inject(this);
        title.setText("设置");
    }

    @OnClick({R.id.iv_titleback, R.id.tv_setting_account, R.id.tv_setting_private, R.id.tv_setting_general, R.id.tv_setting_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_titleback:
                finish();
                break;
            case R.id.tv_setting_account:
                break;
            case R.id.tv_setting_private:
                break;
            case R.id.tv_setting_general:
                break;
            case R.id.tv_setting_exit:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
