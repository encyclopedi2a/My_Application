package com.education.educatenepal.activity.myapplication.json;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.education.educatenepal.activity.myapplication.Beans.JsonListRow;
import com.education.educatenepal.activity.myapplication.adapters.AppAdapter;
import com.education.educatenepal.activity.myapplication.classes.AppController;

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

    public CollegeNameJson(Context context, SwipeMenuListView listView) {
        this.context = context;
        this.listView = listView;
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
                    AppAdapter adapter = new AppAdapter(listItems,context);
                    listView.setAdapter(adapter);
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
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(arrayRequest);
    }

}
