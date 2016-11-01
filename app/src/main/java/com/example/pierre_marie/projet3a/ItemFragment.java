package com.example.pierre_marie.projet3a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.example.pierre_marie.projet3a.R.id.listView;


public class ItemFragment extends Fragment {


    public ListView mListView;
    private List<Monument> mDatalist;
    private ArrayList<ListSample> list = new ArrayList<>();
    private ListSampleAdapter mAdapter;
    private Interf mTunnel;

    public ItemFragment() {
    }

    public static ItemFragment newInstance(int page) {
        Bundle args = new Bundle();
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View View = inflater.inflate(R.layout.fragment_item, container, false);
        mListView = (ListView)View.findViewById(listView);

        mDatalist = mTunnel.getMonumentList();
        list = new ArrayList<>();
        if(!mDatalist.isEmpty()) {
            for (Monument monument : mDatalist) {
                ListSample item = new ListSample(
                        monument.getName(),
                        monument.getLatitude(),
                        monument.getLongitude());
                list.add(item);
            }
        }
        mAdapter= new ListSampleAdapter(getActivity(), list);
        mListView.setAdapter(mAdapter);
        itemlistclicked(mListView);
        return View;
    }

    public void itemlistclicked(View v) {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Intent intent = new Intent();
                intent.setClass(getActivity(), DetailActivity.class);
                intent.putExtra("Monument_lat", Double.toString(mDatalist.get(position).getLatitude()));
                intent.putExtra("Monument_long", Double.toString(mDatalist.get(position).getLongitude()));
                intent.putExtra("Monument_name", (mDatalist.get(position).getName()));
                startActivity(intent);
            }
        });
    }//fonction s'éxécutant quand on click sur un item de la liste des monuments

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
            mTunnel = (Interf) context;
    }
}