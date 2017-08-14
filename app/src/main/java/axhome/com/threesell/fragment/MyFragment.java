package axhome.com.threesell.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.youngkaaa.ycircleview.CircleView;

import org.greenrobot.eventbus.EventBus;

import axhome.com.threesell.R;
import axhome.com.threesell.activity.AdressManagerActivity;
import axhome.com.threesell.activity.MyNewsActivity;
import axhome.com.threesell.activity.MySettingActivity;
import axhome.com.threesell.activity.MyWalletActivity;
import axhome.com.threesell.activity.UserInfoActivity;
import axhome.com.threesell.bean.OrderEvent;
import axhome.com.threesell.bean.UnderEvent;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {


    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.iv_myfragment_headimage)
    CircleView ivMyfragmentHeadimage;
    @InjectView(R.id.tv_myfragment_username)
    TextView tvMyfragmentUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.iv_myfragment_headimage, R.id.iv_myfragment_ewm, R.id.ll_myfragment_order,
            R.id.ll_myfragment_xiaxian, R.id.ll_myfragment_wallet, R.id.ll_myfragment_address, R.id.ll_myfragment_msg,
            R.id.ll_myfragment_setting, R.id.ll_myfragment_about, R.id.ll_myfragment_service, R.id.ll_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_info:
                startActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.iv_myfragment_ewm:
                break;
            case R.id.ll_myfragment_order:
                EventBus.getDefault().post(new OrderEvent());
                break;
            case R.id.ll_myfragment_xiaxian:
                EventBus.getDefault().post(new UnderEvent());
                break;
            case R.id.ll_myfragment_wallet:
                startActivity(new Intent(getActivity(), MyWalletActivity.class));
                break;
            case R.id.ll_myfragment_address:
                startActivity(new Intent(getActivity(), AdressManagerActivity.class));
                break;
            case R.id.ll_myfragment_msg:
                startActivity(new Intent(getActivity(), MyNewsActivity.class));
                break;
            case R.id.ll_myfragment_setting:
                startActivity(new Intent(getActivity(), MySettingActivity.class));
                break;
            case R.id.ll_myfragment_about:
                break;
            case R.id.ll_myfragment_service:
                break;
        }
    }
}
