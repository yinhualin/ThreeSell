package axhome.com.threesell.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import axhome.com.threesell.R;

/**
 * Created by Administrator on 2017/8/8.
 */

public class BuyRecordAdapter extends BaseAdapter {

    private Context context;

    public BuyRecordAdapter(Context context, ArrayList<String> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler vh = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.buyrecord_item, null);
            vh = new ViewHodler();
            vh.tv_goods = (TextView) convertView.findViewById(R.id.tv_goods);
            vh.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            vh.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
            convertView.setTag(vh);
        } else {
            vh = (ViewHodler) convertView.getTag();
        }

        return convertView;
    }

    class ViewHodler {
        TextView tv_goods;
        TextView tv_time;
        TextView tv_money;
    }
}
