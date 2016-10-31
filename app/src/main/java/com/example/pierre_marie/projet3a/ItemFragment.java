package com.example.pierre_marie.projet3a;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

import static com.example.pierre_marie.projet3a.R.id.listView;


public class ItemFragment extends Fragment {


    public ListView mListView;
    private List<Monument> mDatalist;//Liste de station reçu de la requete depuis NavigationActivity
    private ArrayList<ListSample> list = new ArrayList<>();//Entrée du SampleAdapter
    private ListSampleAdapter mAdapter;
    private static String URL_API = "https://www.data.gouv.fr/s/resources/monuments-historiques-francais/20150408-163911/monuments.json";
    private Interf mTunnel;//Interface de communication

    private List<Monument> monumentInfos;

    private RequestQueue mRequestQueue;
    private HttpRequestJson mHttpRequestJson;

    public ItemFragment() {
        //keep empty
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param page Parameter 1.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        list = new ArrayList<>();//Initialisation de list pour l'affichage
        if(!mDatalist.isEmpty()) {//Si la liste n'est pas vide
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



        return View;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


            mTunnel = (Interf) context;

    }

}