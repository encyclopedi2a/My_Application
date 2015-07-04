package com.education.educatenepal.activity.myapplication.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.education.educatenepal.activity.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {
    private Spinner spinner;
    private SwipeMenuCreator swipeMenuCreator;
    private SwipeMenuListView listView;
    private AppAdapter adapter;
    private List<String> mAppList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        //populate the spinner from custom layout adapter
       // new CustomArrayAdapter(getActivity().getApplicationContext(), spinner,null).populateSpinner();
        //initialising listView
        mAppList = new ArrayList<>();
        String[] arrays = getResources().getStringArray(R.array.listArray);
        //Adding item to the arraylist temporarily
        for (String array : arrays)
            mAppList.add(array);
        //
        listView = (SwipeMenuListView) view.findViewById(R.id.listView);
        adapter = new AppAdapter();
        listView.setAdapter(adapter);
        swipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem webpageItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                webpageItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                webpageItem.setWidth(dp2px(50));
                // set item title
                webpageItem.setIcon(R.drawable.webpage);
                // add to menu
                menu.addMenuItem(webpageItem);

                // create "delete" item
                SwipeMenuItem mapItem = new SwipeMenuItem(
                        getActivity().getApplicationContext());
                // set item background
                mapItem.setBackground(new ColorDrawable(Color.rgb(0xC9,
                        0xC9, 0xCE)));
                // set item width
                mapItem.setWidth(dp2px(50));
                // set a icon
                mapItem.setIcon(R.drawable.map);
                // add to menu
                menu.addMenuItem(mapItem);
            }
        };
        // set creator
        listView.setMenuCreator(swipeMenuCreator);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        return view;
    }

    class AppAdapter extends BaseAdapter {

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
                convertView = View.inflate(getActivity().getApplicationContext(),
                        R.layout.item_list_app, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            //holder.iv_icon.setImageDrawable(item.loadIcon(getActivity().getPackageManager()));
            holder.tv_name.setText(mAppList.get(position));
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

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}

