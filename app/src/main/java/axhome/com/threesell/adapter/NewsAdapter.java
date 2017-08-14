package axhome.com.threesell.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import axhome.com.threesell.R;
import axhome.com.threesell.activity.MsgDetailActivity;

/**
 * Created by Administrator on 2017/8/11.
 */

public class NewsAdapter extends BaseAdapter {
    public static Context context;

    public NewsAdapter(Context context, ArrayList<String> list) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_news, null);
            vh = new ViewHolder();
            vh.mTvDetail= (TextView) view.findViewById(R.id.tv_itemnews_detail);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.mTvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MsgDetailActivity.class));
            }
        });
        return view;
    }

    class ViewHolder {
        TextView mTvDetail;
    }
}
