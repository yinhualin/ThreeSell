package axhome.com.threesell.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import axhome.com.threesell.R;

/**
 * Created by Administrator on 2017/8/11.
 */

public class MoneyDetailAdapter extends BaseAdapter {
    private Context context;

    public MoneyDetailAdapter(Context context, ArrayList<String> list) {
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
            convertView = View.inflate(context, R.layout.item_moneydetail, null);
            vh = new ViewHodler();
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
