package com.education.educatenepal.activity.myapplication.classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.education.educatenepal.activity.myapplication.R;
import com.education.educatenepal.activity.myapplication.interfaces.Item;

import java.util.ArrayList;

@SuppressLint("InflateParams")
public class EntryAdapter extends ArrayAdapter<Item> {
    @SuppressWarnings("unused")
    private Context context;
    private ArrayList<Item> items;
    private LayoutInflater vi;

    public EntryAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        final Item i = items.get(position);
        if (i != null) {
            if (i.isSection()) {
                SectionItem si = (SectionItem) i;
                v = vi.inflate(R.layout.list_item_section, null);
                v.setOnClickListener(null);
                v.setOnLongClickListener(null);
                v.setLongClickable(false);

                final TextView sectionView = (TextView) v
                        .findViewById(R.id.list_item_section_text);
                sectionView.setTextSize(19);
                sectionView.setPadding(12, 12, 12, 12);
                sectionView.setTypeface(null, Typeface.BOLD);
                sectionView.setText(si.title);

            } else {
                EntryItem ei = (EntryItem) i;
                v = vi.inflate(R.layout.list_item_enty, null);
                final TextView title = (TextView) v
                        .findViewById(R.id.list_item_entry_title);
                if(position==0) {
                    title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home, 0, 0, 0);
                }
                title.setTextColor(Color.BLACK);
                title.setBackgroundResource(R.drawable.roundedcorner);
                if (title != null)
                    title.setText(ei.title);
            }
        }
        return v;
    }
}
