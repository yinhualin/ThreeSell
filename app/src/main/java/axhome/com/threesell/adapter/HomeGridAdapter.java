package axhome.com.threesell.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import axhome.com.threesell.R;

/**
 * Created by Administrator on 2017/7/29.
 */

public class HomeGridAdapter extends BaseAdapter {

    private Context context;

    public HomeGridAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
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
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = View.inflate(context, R.layout.homedetail_item, null);
            viewHolder = new ViewHolder();
            viewHolder.homedetail_iv = (ImageView) convertView.findViewById(R.id.homedetail_iv);
            viewHolder.homedetail_tv = (TextView) convertView.findViewById(R.id.homedetail_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    class ViewHolder {
        ImageView homedetail_iv;
        TextView homedetail_tv;
    }
}
