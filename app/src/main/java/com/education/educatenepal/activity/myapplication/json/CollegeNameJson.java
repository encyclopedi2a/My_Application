package com.education.educatenepal.activity.myapplication.json;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.education.educatenepal.activity.myapplication.Beans.JsonListRow;
import com.education.educatenepal.activity.myapplication.adapters.JSONListBaseAdapter;
import com.education.educatenepal.activity.myapplication.classes.AppController;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gokarna on 7/5/15.
 */
public class CollegeNameJson {
    private Context context;
    private SwipeMenuListView listView;
    private String arrayUrl = "http://thesunbihosting.com/demo/beauty/json/opening_days";
    private List<JsonListRow> listItems = new ArrayList<>();
    private CircleProgressBar circleProgressBar;

    public CollegeNameJson(Context context, SwipeMenuListView listView, CircleProgressBar circleProgressBar) {
        this.context = context;
        this.listView = listView;
        this.circleProgressBar = circleProgressBar;
    }

    public void makeJsonArrayRequest() {
        JsonArrayRequest arrayRequest = new JsonArrayRequest(arrayUrl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    // Parsing json array response
                    // loop through each json object
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject person = (JSONObject) response
                                .getJSONObject(i);
                        String collegeName = person.getString("day");
                        JsonListRow item = new JsonListRow(collegeName);
                        listItems.add(item);
                    }
                    JSONListBaseAdapter adapter = new JSONListBaseAdapter(listItems, context);
                    listView.setAdapter(adapter);
                    circleProgressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

                //hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(arrayRequest);
    }

}
