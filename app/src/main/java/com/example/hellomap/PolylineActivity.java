package com.example.hellomap;

/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

/**
 * This shows how to draw polylines on a map.
 */
public class PolylineActivity extends AppCompatActivity implements OnMapReadyCallback {

    // Locations for Route 1
    private static final LatLng R1_POINT_1 = new LatLng(41.607094, 0.621620);
    private static final LatLng R1_POINT_2 = new LatLng(41.609380, 0.624281);
    private static final LatLng R1_POINT_3 = new LatLng(41.612388, 0.619313);
    private static final LatLng R1_POINT_4 = new LatLng(41.615794, 0.620863);

    // Locations for Route 2
    private static final LatLng R2_POINT_1 = new LatLng(41.608735, 0.627812);
    private static final LatLng R2_POINT_2 = new LatLng(41.609489, 0.626525);
    private static final LatLng R2_POINT_3 = new LatLng(41.611182, 0.627458);
    private static final LatLng R2_POINT_4 = new LatLng(41.612128, 0.628627);
    private static final LatLng R2_POINT_5 = new LatLng(41.612284, 0.631406);
    private static final LatLng R2_POINT_6 = new LatLng(41.613724, 0.630167);
    private static final LatLng R2_POINT_7 = new LatLng(41.615108, 0.627565);

    private static final int CUSTOM_CAP_IMAGE_REF_WIDTH_PX = 50;

    CheckBox cb1, cb2;

    private Polyline polyline1;
    private Polyline polyline2;

    private GoogleMap g_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polylines_demo);

        cb1 = (CheckBox)findViewById(R.id.checkBox1);
        cb2 = (CheckBox)findViewById(R.id.checkBox2);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private String[] getResourceStrings(int[] resourceIds) {
        String[] strings = new String[resourceIds.length];
        for (int i = 0; i < resourceIds.length; i++) {
            strings[i] = getString(resourceIds[i]);
        }
        return strings;
    }

    @Override
    public void onMapReady(GoogleMap map) {

        // Override the default content description on the view, for accessibility mode.
        map.setContentDescription(getString(R.string.polyline_demo_description));
        g_map = map;
    }

    public void showRuta1(View view){

        if(cb1.isChecked()){
            // A simple polyline. This polyline will be mutable.
            int color = Color.HSVToColor(255, new float[]{1, 1, 1});

            polyline1 = g_map.addPolyline(new PolylineOptions()
                    .color(color)
                    .width(20)
                    .clickable(false)
                    .add(R1_POINT_1, R1_POINT_2, R1_POINT_3, R1_POINT_4));

            polyline1.setStartCap(new CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.chevron), 50));
            polyline1.setEndCap(new CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.meta), 300));
            polyline1.setJointType(JointType.DEFAULT);
            polyline1.setPattern(null);

            // Move the map so that it is centered on the mutable polyline.
            g_map.moveCamera(CameraUpdateFactory.newLatLngZoom(R1_POINT_2, 15));
        } else {
            if (polyline1 != null){
                polyline1.remove();
            }
        }
    }

    public void showRuta2(View view){
        if(cb2.isChecked()){
            // A simple polyline. This polyline will be mutable.
            int color = Color.HSVToColor(255, new float[]{1, 1, 1});

            polyline2 = g_map.addPolyline(new PolylineOptions()
                    .color(color)
                    .width(20)
                    .clickable(false)
                    .add(R2_POINT_1, R2_POINT_2, R2_POINT_3, R2_POINT_4, R2_POINT_5, R2_POINT_6, R2_POINT_7));

            polyline2.setStartCap(new CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.chevron), CUSTOM_CAP_IMAGE_REF_WIDTH_PX));
            polyline2.setEndCap(new CustomCap(BitmapDescriptorFactory.fromResource(R.drawable.meta), 300));
            polyline2.setJointType(JointType.DEFAULT);
            polyline2.setPattern(null);

            // Move the map so that it is centered on the mutable polyline.
            g_map.moveCamera(CameraUpdateFactory.newLatLngZoom(R2_POINT_2, 15));
        } else {
            if (polyline2 != null){
                polyline2.remove();
            }
        }
    }
}