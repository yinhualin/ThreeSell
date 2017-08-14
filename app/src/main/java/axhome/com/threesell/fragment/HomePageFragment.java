package axhome.com.threesell.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import java.util.ArrayList;
import java.util.List;

import axhome.com.threesell.R;
import axhome.com.threesell.activity.ConfigOrderActivity;
import axhome.com.threesell.activity.HomeAdActivity;
import axhome.com.threesell.adapter.HomeGridAdapter;
import axhome.com.threesell.view.MyGridView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    @InjectView(R.id.home_money_tv)
    TextView homeMoneyTv;
    @InjectView(R.id.home_jifen_tv)
    TextView homeJifenTv;
    @InjectView(R.id.home_head_iv)
    ImageView homeHeadIv;
    @InjectView(R.id.home_name_tv)
    TextView homeNameTv;
    @InjectView(R.id.home_news_tv)
    TextView homeNewsTv;
    @InjectView(R.id.home_news_ll)
    LinearLayout homeNewsLl;
    @InjectView(R.id.home_registertime_tv)
    TextView homeRegistertimeTv;
    @InjectView(R.id.home_registertime_ll)
    LinearLayout homeRegistertimeLl;
    @InjectView(R.id.home_underperson_tv)
    TextView homeUnderpersonTv;
    @InjectView(R.id.home_underperson_ll)
    LinearLayout homeUnderpersonLl;
    @InjectView(R.id.home_convenientBanner)
    ConvenientBanner homeConvenientBanner;
    @InjectView(R.id.home_computer_ll)
    LinearLayout homeComputerLl;
    @InjectView(R.id.homedetail_rl)
    RelativeLayout homedetailRl;
    @InjectView(R.id.home_goodsname_tv)
    TextView homeGoodsnameTv;
    @InjectView(R.id.home_goodsprice)
    TextView homeGoodsprice;
    @InjectView(R.id.home_goodsoldprice)
    TextView homeGoodsoldprice;
    @InjectView(R.id.home_goodssellnum_tv)
    TextView homeGoodssellnumTv;
    @InjectView(R.id.home_mygridview)
    MyGridView homeMygridview;
    @InjectView(R.id.imageView4)
    ImageView imageView4;
    private List<Integer> imgs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    private void initView() {
        //初始化轮播图

        imgs.add(R.drawable.a);
        imgs.add(R.drawable.b);
        imgs.add(R.drawable.c);
        imgs.add(R.drawable.d);
        homeConvenientBanner.startTurning(2000);
        homeConvenientBanner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new LocalImageHolderView();
            }
        }, imgs)
                .setPointViewVisible(false);

        HomeGridAdapter hgadapter = new HomeGridAdapter(getActivity());
        homeMygridview.setAdapter(hgadapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.home_news_ll, R.id.home_buynow_tv,R.id.imageView4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_news_ll:

                break;
            case R.id.home_buynow_tv:
                startActivity(new Intent(getActivity(), ConfigOrderActivity.class));
                break;
            case R.id.imageView4:
                startActivity(new Intent(getActivity(), HomeAdActivity.class));
                break;
        }
    }

    public class LocalImageHolderView implements Holder<Integer> {

        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Integer data) {
            imageView.setImageResource(data);
        }
    }
    //
}
