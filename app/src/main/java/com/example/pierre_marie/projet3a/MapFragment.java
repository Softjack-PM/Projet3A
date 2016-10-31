package com.example.pierre_marie.projet3a;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.example.pierre_marie.projet3a.R.id.map;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private MapView mMapView;
    private GoogleMap mMap;
    // TODO: Rename and change types of parameters



    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onMapReady(GoogleMap GoogleMap) {
        mMap = GoogleMap;
        //mMap.getUiSettings().setZoomControlsEnabled(true);

        LatLng gardanne = new LatLng(43.45, 5.46);
        mMap.addMarker(new MarkerOptions().position(gardanne).title("Mines St Etienne"));
        //mMap.getUiSettings().setZoomGesturesEnabled(true);
        //moovecamera
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(10, 10))
                .title("Hello world"));
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_map, container, false);
        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);


    }



}