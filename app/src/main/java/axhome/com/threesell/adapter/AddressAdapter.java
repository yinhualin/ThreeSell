package axhome.com.threesell.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.HashMap;

import axhome.com.threesell.R;
import axhome.com.threesell.activity.NewAddressActivity;

/**
 * Created by Administrator on 2017/8/10.
 */

public class AddressAdapter extends BaseAdapter {
    private Context context;
    private String[] beans;
    // 用于记录每个RadioButton的状态，并保证只可选一个
    HashMap<String, Boolean> states = new HashMap<String, Boolean>();

    class ViewHolder {
        TextView tv_address_edit;
        RadioButton rb_state;
    }

    public AddressAdapter(Context context, String[] beans) {
        // TODO Auto-generated constructor stub
        this.beans = beans;
        this.context = context;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 5;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return beans[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        // 页面
        ViewHolder holder;
        String bean = beans[position];
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(
                    R.layout.address_item, null);
            holder = new ViewHolder();
            TextView tv_addressname = (TextView) convertView.findViewById(R.id.tv_addressname);
            TextView tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            holder.tv_address_edit = (TextView) convertView.findViewById(R.id.tv_address_edit);
            TextView tv_address_delete = (TextView) convertView.findViewById(R.id.tv_address_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_address_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, NewAddressActivity.class));
            }
        });
        final RadioButton rb_address = (RadioButton) convertView.findViewById(R.id.rb_address);
        holder.rb_state = rb_address;
        holder.rb_state.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                // 重置，确保最多只有一项被选中
                for (String key : states.keySet()) {
                    states.put(key, false);

                }
                states.put(String.valueOf(position), rb_address.isChecked());
                AddressAdapter.this.notifyDataSetChanged();
            }
        });

        boolean res = false;
        if (states.get(String.valueOf(position)) == null
                || states.get(String.valueOf(position)) == false) {
            res = false;
            states.put(String.valueOf(position), false);
        } else
            res = true;

        holder.rb_state.setChecked(res);
        return convertView;
    }
}
