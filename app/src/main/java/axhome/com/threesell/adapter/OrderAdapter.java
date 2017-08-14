package axhome.com.threesell.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import axhome.com.threesell.R;

/**
 * Created by Administrator on 2017/8/9.
 */

public class OrderAdapter extends BaseAdapter {
    private Context context;

    public OrderAdapter(Context context, ArrayList<String> list) {
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
            view = View.inflate(context, R.layout.order_item, null);

            vh = new ViewHolder();
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }

        return view;
    }

    class ViewHolder {

    }
}
