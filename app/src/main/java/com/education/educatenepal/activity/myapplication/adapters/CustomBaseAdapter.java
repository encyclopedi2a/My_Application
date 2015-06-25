package com.education.educatenepal.activity.myapplication.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.education.educatenepal.activity.myapplication.Beans.RowItem;
import com.education.educatenepal.activity.myapplication.R;

import java.util.List;


/**
 * Created by gokarna on 6/19/15.
 */
public class CustomBaseAdapter extends BaseAdapter {
    private Context context;
    private List<RowItem> list;

    public CustomBaseAdapter(Context context, List<RowItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_row_item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.title.setBackgroundColor(Color.parseColor("#d3d3d3"));
            holder.webpage = (ImageView) convertView.findViewById(R.id.webpage);
            holder.webpage.setBackgroundColor(Color.LTGRAY);
            holder.map = (ImageView) convertView.findViewById(R.id.map);
            holder.map.setBackgroundColor(Color.LTGRAY);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        RowItem rowItem = (RowItem) getItem(position);
        holder.title.setText(rowItem.getTitle());
        holder.webpage.setImageResource(R.drawable.webpage);
        holder.map.setImageResource(R.drawable.map);

        return convertView;

    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView webpage, map;
        TextView title;
    }
}
