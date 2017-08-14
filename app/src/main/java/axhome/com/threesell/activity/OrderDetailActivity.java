package axhome.com.threesell.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import axhome.com.threesell.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class OrderDetailActivity extends AppCompatActivity {

    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.iv_order)
    ImageView ivOrder;
    @InjectView(R.id.tv_order)
    TextView tvOrder;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_address)
    TextView tvAddress;
    @InjectView(R.id.iv_companyhead)
    ImageView ivCompanyhead;
    @InjectView(R.id.tv_companyname)
    TextView tvCompanyname;
    @InjectView(R.id.tv_orderstate)
    TextView tvOrderstate;
    @InjectView(R.id.iv_goods)
    ImageView ivGoods;
    @InjectView(R.id.tv_goodsname)
    TextView tvGoodsname;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.tv_oldprice)
    TextView tvOldprice;
    @InjectView(R.id.tv_count)
    TextView tvCount;
    @InjectView(R.id.btn1)
    Button btn1;
    @InjectView(R.id.btn2)
    Button btn2;
    @InjectView(R.id.tv_od_num)
    TextView tvOdNum;
    @InjectView(R.id.tv_od_ordertime)
    TextView tvOdOrdertime;
    @InjectView(R.id.tv_od_paytime)
    TextView tvOdPaytime;
    @InjectView(R.id.tv_od_sendtime)
    TextView tvOdSendtime;
    @InjectView(R.id.tv_od_price)
    TextView tvOdPrice;
    @InjectView(R.id.tv_od_yunfei)
    TextView tvOdYunfei;
    @InjectView(R.id.tv_od_realpay)
    TextView tvOdRealpay;
    @InjectView(R.id.ll)
    LinearLayout ll;
    private String type;
    private DeletePopWindow deletepop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.inject(this);
        title.setText("订单详情");
        type = getIntent().getStringExtra("type");
        initView();
    }

    private void initView() {
        switch (type) {
            case "1":
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deletepop = new DeletePopWindow(OrderDetailActivity.this);
                        deletepop.showAtLocation(ll, Gravity.CENTER, 0, 0);
                        deletepop.setOnDismissListener(new PopupWindow.OnDismissListener() {

                            @Override
                            public void onDismiss() {
                                WindowManager.LayoutParams lp = getWindow().getAttributes();
                                lp.alpha = 1f;
                                getWindow().setAttributes(lp);
                            }
                        });
                    }
                });
                break;
            case "2":
                ivOrder.setImageResource(R.drawable.ordernopay);
                tvOrder.setText("等待付款");
                btn1.setText("取消订单");
                btn2.setText("付款");
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CanclePopWindow canclePopWindow = new CanclePopWindow(OrderDetailActivity.this);
                        canclePopWindow.showAtLocation(ll, Gravity.CENTER, 0, 0);
                        canclePopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

                            @Override
                            public void onDismiss() {
                                WindowManager.LayoutParams lp = getWindow().getAttributes();
                                lp.alpha = 1f;
                                getWindow().setAttributes(lp);
                            }
                        });

                    }
                });
                break;
            case "3":
                ivOrder.setImageResource(R.drawable.ordershouhuo);
                tvOrder.setText("卖家已发货");
                btn1.setText("再次购买");
                btn2.setText("退款");
                break;
        }
    }

    @OnClick(R.id.iv_titleback)
    public void onViewClicked() {
        finish();
    }

    public class DeletePopWindow extends PopupWindow implements View.OnClickListener {
        private View conentView;

        public DeletePopWindow(final Activity context) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            conentView = inflater.inflate(R.layout.delete_popwindow, null);
            TextView cancle = (TextView) conentView.findViewById(R.id.delete_cancle);
            TextView config = (TextView) conentView.findViewById(R.id.delete_config);
            cancle.setOnClickListener(this);
            config.setOnClickListener(this);
            int h = context.getWindowManager().getDefaultDisplay().getHeight();
            int w = context.getWindowManager().getDefaultDisplay().getWidth();
            // 设置SelectPicPopupWindow的View
            this.setContentView(conentView);
            // 设置SelectPicPopupWindow弹出窗体的宽
            this.setWidth(w - 50);
            // 设置SelectPicPopupWindow弹出窗体的高
            this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            // 设置SelectPicPopupWindow弹出窗体可点击
            this.setFocusable(true);
            this.setOutsideTouchable(true);
            // 刷新状态
            this.update();
            // 实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0000000000);
            // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
            this.setBackgroundDrawable(dw);
            // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            // 设置SelectPicPopupWindow弹出窗体动画效果
            this.setAnimationStyle(R.style.AnimationPreview);
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha = 0.7f;
            getWindow().setAttributes(lp);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.delete_cancle:
                    dismiss();
                    break;
                case R.id.delete_config:
                    dismiss();
                    break;
            }
        }
    }

    public class CanclePopWindow extends PopupWindow implements View.OnClickListener {
        private View conentView;

        public CanclePopWindow(final Activity context) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            conentView = inflater.inflate(R.layout.order_cancle, null);
            RadioGroup rg = (RadioGroup) conentView.findViewById(R.id.rg);
            TextView cancle_cancle = (TextView) conentView.findViewById(R.id.cancle_cancle);
            TextView cancle_config = (TextView) conentView.findViewById(R.id.cancle_config);
            cancle_config.setOnClickListener(this);
            cancle_cancle.setOnClickListener(this);
            int h = context.getWindowManager().getDefaultDisplay().getHeight();
            int w = context.getWindowManager().getDefaultDisplay().getWidth();
            // 设置SelectPicPopupWindow的View
            this.setContentView(conentView);
            // 设置SelectPicPopupWindow弹出窗体的宽
            this.setWidth(w - 50);
            // 设置SelectPicPopupWindow弹出窗体的高
            this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            // 设置SelectPicPopupWindow弹出窗体可点击
            this.setFocusable(true);
            this.setOutsideTouchable(true);
            // 刷新状态
            this.update();
            // 实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0000000000);
            // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
            this.setBackgroundDrawable(dw);
            // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            // 设置SelectPicPopupWindow弹出窗体动画效果
            this.setAnimationStyle(R.style.AnimationPreview);
            // 设置背景颜色变暗
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha = 0.7f;
            getWindow().setAttributes(lp);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.cancle_cancle:
                    dismiss();
                    break;
                case R.id.cancle_config:
                    dismiss();
                    break;
            }
        }
    }

}
