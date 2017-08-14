package axhome.com.threesell.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import axhome.com.threesell.R;
import axhome.com.threesell.activity.PersonDetailActivity;
import axhome.com.threesell.adapter.PersonAdapter;
import axhome.com.threesell.bean.PersonBean;
import axhome.com.threesell.view.MyListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment implements AdapterView.OnItemClickListener {


    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.iv1)
    ImageView iv1;
    @InjectView(R.id.tv1)
    TextView tv1;
    @InjectView(R.id.mlv1)
    MyListView mlv1;
    @InjectView(R.id.iv2)
    ImageView iv2;
    @InjectView(R.id.tv2)
    TextView tv2;
    @InjectView(R.id.mlv2)
    MyListView mlv2;
    @InjectView(R.id.iv3)
    ImageView iv3;
    @InjectView(R.id.tv3)
    TextView tv3;
    @InjectView(R.id.mlv3)
    MyListView mlv3;
    boolean s1 = true;
    boolean s2 = true;
    boolean s3 = true;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        ButterKnife.inject(this, view);
        initView();
        return view;
    }

    //
    private void initView() {
        title.setText("通讯录");
        ArrayList<PersonBean> pbean = new ArrayList<>();
        PersonAdapter pAdapter = new PersonAdapter(getActivity(), pbean);
        mlv1.setAdapter(pAdapter);
        mlv2.setAdapter(pAdapter);
        mlv3.setAdapter(pAdapter);
        mlv1.setOnItemClickListener(this);
        mlv2.setOnItemClickListener(this);
        mlv3.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ll1, R.id.ll2, R.id.ll3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll1:
                if (s1) {
                    mlv1.setVisibility(View.VISIBLE);
                    iv1.setImageResource(R.drawable.downangle);
                    s1 = false;
                } else {
                    mlv1.setVisibility(View.GONE);
                    iv1.setImageResource(R.drawable.righttriangle);
                    s1 = true;
                }
                break;
            case R.id.ll2:
                if (s2) {
                    mlv2.setVisibility(View.VISIBLE);
                    iv2.setImageResource(R.drawable.downangle);
                    s2 = false;
                } else {
                    mlv2.setVisibility(View.GONE);
                    iv2.setImageResource(R.drawable.righttriangle);
                    s2 = true;
                }

                break;
            case R.id.ll3:
                if (s3) {
                    mlv3.setVisibility(View.VISIBLE);
                    iv3.setImageResource(R.drawable.downangle);
                    s3 = false;
                } else {
                    mlv3.setVisibility(View.GONE);
                    iv3.setImageResource(R.drawable.righttriangle);
                    s3 = true;
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.mlv1:
                startActivity(new Intent(getActivity(), PersonDetailActivity.class));
                break;
            case R.id.mlv2:
                startActivity(new Intent(getActivity(), PersonDetailActivity.class));
                break;
            case R.id.mlv3:
                startActivity(new Intent(getActivity(), PersonDetailActivity.class));
                break;
        }
    }
}
