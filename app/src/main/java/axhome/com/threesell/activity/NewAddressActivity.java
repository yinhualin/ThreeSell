package axhome.com.threesell.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.db.manager.AddressDictManager;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;

import axhome.com.threesell.R;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NewAddressActivity extends AppCompatActivity implements View.OnClickListener, OnAddressSelectedListener, AddressSelector.OnDialogCloseListener {
    @InjectView(R.id.iv_titleback)
    ImageView ivTitleback;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.et_name)
    EditText etName;
    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.tv_diqu)
    TextView tvDiqu;
    @InjectView(R.id.ll_address)
    LinearLayout llAddress;
    @InjectView(R.id.et_addressdetail)
    EditText etAddressdetail;
    @InjectView(R.id.btn_save)
    Button btnSave;
    @InjectView(R.id.rl)
    RelativeLayout rl;
    private BottomDialog dialog;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String streetCode;
    private AddressDictManager addressDictManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_address);
        ButterKnife.inject(this);
        title.setText("收货地址管理");
        llAddress.setOnClickListener(this);
        AddressSelector selector = new AddressSelector(this);
        //获取地址管理数据库
        addressDictManager = selector.getAddressDictManager();

        selector.setTextSize(14);//设置字体的大小
//        selector.setIndicatorBackgroundColor("#00ff00");
        selector.setIndicatorBackgroundColor(android.R.color.holo_orange_light);//设置指示器的颜色
//        selector.setBackgroundColor(android.R.color.holo_red_light);//设置字体的背景

        selector.setTextSelectedColor(android.R.color.holo_orange_light);//设置字体获得焦点的颜色

        selector.setTextUnSelectedColor(android.R.color.holo_blue_light);//设置字体没有获得焦点的颜色

    }

    @Override
    public void onClick(View view) {
        if (dialog != null) {
            dialog.show();
        } else {
            dialog = new BottomDialog(this);
            dialog.setOnAddressSelectedListener(this);
            dialog.setDialogDismisListener(this);
            dialog.setTextSize(14);//设置字体的大小
            dialog.setIndicatorBackgroundColor(android.R.color.holo_orange_light);//设置指示器的颜色
            dialog.setTextSelectedColor(android.R.color.holo_orange_light);//设置字体获得焦点的颜色
            dialog.setTextUnSelectedColor(android.R.color.holo_blue_light);//设置字体没有获得焦点的颜色
            dialog.show();
        }
    }


    public void onAddressSelected(Province province, City city, County county, Street street) {
        provinceCode = (province == null ? "" : province.code);
        cityCode = (city == null ? "" : city.code);
        countyCode = (county == null ? "" : county.code);
        streetCode = (street == null ? "" : street.code);
        String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                (street == null ? "" : street.name);
        tvDiqu.setText(s);
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
    @OnClick({R.id.iv_titleback, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_titleback:
                finish();
                break;
            case R.id.btn_save:
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}

