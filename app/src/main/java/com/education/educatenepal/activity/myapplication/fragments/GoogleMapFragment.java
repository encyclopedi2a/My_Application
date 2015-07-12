package com.education.educatenepal.activity.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.education.educatenepal.activity.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class GoogleMapFragment extends Fragment implements View.OnClickListener {
    private GoogleMap googleMap;
    private MapView mapView;
    private LatLng LAT_LNG = null;
    private String title = null;
    private Marker Lat_Lng;
    private Button normalButton,satelliteButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_googlemap, container, false);
        normalButton=(Button)view.findViewById(R.id.normal);
        normalButton.setOnClickListener(this);
        satelliteButton=(Button)view.findViewById(R.id.satellite);
        satelliteButton.setOnClickListener(this);
        //This value comes from following process
        //DashboardActivity->ViewPagerFragment-->GoogleMapFragment
        String listPosition = getArguments().getString("position");
        if (listPosition.equals("2")) {
            LAT_LNG = new LatLng(27.679404, 85.286473);

        } else if (listPosition.equals("3")) {
            LAT_LNG = new LatLng(28.145302, 84.083793);
        } else if (listPosition.equals("4")) {
            LAT_LNG = new LatLng(26.682404, 87.355015);
        } else if (listPosition.equals("5")) {
            LAT_LNG = new LatLng(27.618164, 85.537403);
        } else if (listPosition.equals("6")) {
            LAT_LNG = new LatLng(27.707638, 83.463264);
        } else if (listPosition.equals("7")) {
            LAT_LNG = new LatLng(27.940281, 83.439474);
        }
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        googleMap = mapView.getMap();
        if (googleMap != null) {
            Lat_Lng = googleMap.addMarker(new MarkerOptions().position(LAT_LNG));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LAT_LNG, 1000));

// Zoom in, animating the camera.
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 3000, null);
        }
        return view;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onClick(View view) {
        if(view==(Button)normalButton){
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        else if(view==(Button)satelliteButton){
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
    }
}
