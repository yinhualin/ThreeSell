package axhome.com.threesell;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.fe.library.TabContainerView;
import com.fe.library.adapter.DefaultAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import axhome.com.threesell.bean.OrderEvent;
import axhome.com.threesell.bean.UnderEvent;
import axhome.com.threesell.fragment.ContactsFragment;
import axhome.com.threesell.fragment.HomePageFragment;
import axhome.com.threesell.fragment.MyFragment;
import axhome.com.threesell.fragment.OrderFragment;

public class MainActivity extends AppCompatActivity {


    private TabContainerView tabContainerView;
    private int[] iconImageArray, selectedIconImageArray;
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        iconImageArray = new int[]{R.drawable.homepage, R.drawable.contacts, R.drawable.order, R.drawable.my};
        selectedIconImageArray = new int[]{R.drawable.homepagegreen, R.drawable.contactsgreen, R.drawable.ordergreen, R.drawable.mygreen};
        fragments = new Fragment[]{new HomePageFragment(), new ContactsFragment(), new OrderFragment(), new MyFragment()};

        tabContainerView = (TabContainerView) findViewById(R.id.tab_containerview_main);
        tabContainerView.setTabHostBgColor(Color.parseColor("#F2F2F2"));
        tabContainerView.setAdapter(new DefaultAdapter(this, fragments, getSupportFragmentManager(), getResources().getStringArray(R.array.titleArray),
                getResources().getColor(R.color.colorPrimary), iconImageArray, selectedIconImageArray));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello(OrderEvent event) {
        /* Do something */
        tabContainerView.setCurrentItem(2);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello(UnderEvent event) {
        /* Do something */
        tabContainerView.setCurrentItem(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
