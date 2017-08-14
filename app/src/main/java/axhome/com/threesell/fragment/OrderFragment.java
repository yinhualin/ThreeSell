package axhome.com.threesell.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

import axhome.com.threesell.R;
import axhome.com.threesell.activity.OrderDetailActivity;
import axhome.com.threesell.adapter.OrderAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.tv_allorder)
    TextView tvAllorder;
    @InjectView(R.id.tv_nopay)
    TextView tvNopay;
    @InjectView(R.id.tv_shouhuo)
    TextView tvShouhuo;
    @InjectView(R.id.view1)
    View view1;
    @InjectView(R.id.view2)
    View view2;
    @InjectView(R.id.view3)
    View view3;
    @InjectView(R.id.order_plv)
    PullToRefreshListView orderPlv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.inject(this, view);
        initView();

        return view;
    }

    private void initView() {
        title.setText("订单");
        ArrayList<String> list = new ArrayList<>();
        OrderAdapter oAdapter = new OrderAdapter(getActivity(), list);
        orderPlv.setAdapter(oAdapter);
        orderPlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                intent.putExtra("type", "1");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.tv_allorder, R.id.tv_nopay, R.id.tv_shouhuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_allorder:
                initColor();
                tvAllorder.setTextColor(Color.parseColor("#89BA05"));
                view1.setVisibility(View.VISIBLE);
                orderPlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                        intent.putExtra("type", "1");
                        startActivity(intent);
                    }
                });
                break;
            case R.id.tv_nopay:
                initColor();
                tvNopay.setTextColor(Color.parseColor("#89BA05"));
                view2.setVisibility(View.VISIBLE);
                orderPlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                        intent.putExtra("type", "2");
                        startActivity(intent);
                    }
                });
                break;
            case R.id.tv_shouhuo:
                initColor();
                tvShouhuo.setTextColor(Color.parseColor("#89BA05"));
                view3.setVisibility(View.VISIBLE);
                orderPlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                        intent.putExtra("type", "3");
                        startActivity(intent);
                    }
                });
                break;
        }
    }

    private void initColor() {
        tvAllorder.setTextColor(Color.BLACK);
        tvNopay.setTextColor(Color.BLACK);
        tvShouhuo.setTextColor(Color.BLACK);
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
    }
}
