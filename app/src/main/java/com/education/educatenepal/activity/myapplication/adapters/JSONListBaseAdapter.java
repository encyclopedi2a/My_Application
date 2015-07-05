package com.education.educatenepal.activity.myapplication.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.education.educatenepal.activity.myapplication.Beans.JsonListRow;
import com.education.educatenepal.activity.myapplication.R;

import java.util.List;

/**
 * Created by gokarna on 7/5/15.
 */
public class JSONListBaseAdapter extends BaseAdapter {
    private List<JsonListRow> mAppList;
    private Context context;
    public JSONListBaseAdapter(List<JsonListRow> mAppList, Context context){
        this.mAppList=mAppList;
        this.context=context;
    }
    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context.getApplicationContext(),
                    R.layout.item_list_app, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        JsonListRow listRow=(JsonListRow) getItem(position);
        holder.tv_name.setText(listRow.getCollegeName());
        holder.tv_name.setBackgroundResource(R.drawable.selector_state);
        return convertView;
    }

    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;

        public ViewHolder(View view) {
            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(this);
        }
    }
}
