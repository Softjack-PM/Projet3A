package com.example.pierre_marie.projet3a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;


import java.util.ArrayList;
import java.util.List;


public class ItemFragment extends Fragment implements SearchView.OnQueryTextListener{


    public ListView mListView;
    private SearchView mSearchView;
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
        mListView = (ListView)View.findViewById(R.id.listView);
        mSearchView = (SearchView)View.findViewById(R.id.searchView);

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

        mListView.setTextFilterEnabled(true);
        setupSearchView();

        return View;
    }

    private void setupSearchView() {
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setQueryHint("Search Monuments");
    }

    public void itemlistclicked(View v) {

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Intent intent = new Intent();
                intent.setClass(getActivity(), DetailActivity.class);
                intent.putExtra("Monument_latitude", Double.toString(mDatalist.get(position).getLatitude()));
                intent.putExtra("Monument_longitude", Double.toString(mDatalist.get(position).getLongitude()));


                startActivity(intent);
            }
        });
    }//fonction s'éxécutant quand on click sur un item de la liste des monuments

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
            mTunnel = (Interf) context;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            mListView.clearTextFilter();
        } else {
            mListView.setFilterText(newText.toString());
        }
        return true;
    }
}