package com.example.pierre_marie.projet3a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ListSampleAdapter extends ArrayAdapter<ListSample>{

    public ListSampleAdapter(Context context, List<ListSample> monument) {
        super(context, 0, monument);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listeitem, parent, false);
        TextView name = (TextView) itemView.findViewById(R.id.name);
        TextView latitude = (TextView) itemView.findViewById(R.id.latitude);
        TextView longitude = (TextView) itemView.findViewById(R.id.longitude);

        ListSample sampleData = getItem(position);

        name.setText(sampleData.getName());
        latitude.setText("Latitude : " + String.valueOf(sampleData.getLatitude()));
        longitude.setText("Longitude : " + String.valueOf(sampleData.getLongitude() + "\n"));

        return itemView;
    }
}