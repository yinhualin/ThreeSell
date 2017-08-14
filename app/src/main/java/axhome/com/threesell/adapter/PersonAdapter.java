package axhome.com.threesell.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.youngkaaa.ycircleview.CircleView;

import java.util.ArrayList;

import axhome.com.threesell.R;
import axhome.com.threesell.bean.PersonBean;

/**
 * Created by Administrator on 2017/8/8.
 */

public class PersonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PersonBean> list;

    public PersonAdapter(Context context, ArrayList<PersonBean> pbean) {
        this.context = context;
        this.list = pbean;
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
        ViewHolder vh = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.person_item, null);
            vh = new ViewHolder();
            vh.cv_head = (CircleView) convertView.findViewById(R.id.cv_head);
            vh.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            vh.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    class ViewHolder {
        CircleView cv_head;
        TextView tv_name;
        TextView tv_time;
    }
}
