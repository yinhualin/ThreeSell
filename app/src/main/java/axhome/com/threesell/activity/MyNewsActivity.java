package axhome.com.threesell.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import axhome.com.threesell.R;
import axhome.com.threesell.adapter.NewsAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MyNewsActivity extends AppCompatActivity {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.lv_mynews)
    ListView lvMynews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_my_news);
        ButterKnife.inject(this);
        initView();
    }

    private void initView() {
        title.setText("消息");
        NewsAdapter newsAdapter = new NewsAdapter(this, new ArrayList<String>());
        lvMynews.setAdapter(newsAdapter);
    }

    @OnClick(R.id.iv_titleback)
    public void onViewClicked() {
        finish();
    }
}
