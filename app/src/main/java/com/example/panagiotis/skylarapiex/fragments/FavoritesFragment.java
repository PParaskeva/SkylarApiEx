package com.example.panagiotis.skylarapiex.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.panagiotis.skylarapiex.R;
import com.example.panagiotis.skylarapiex.adapter.FavoriteAdapter;
import com.example.panagiotis.skylarapiex.model.realm.Favorites;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;


public class FavoritesFragment extends Fragment {

    @BindView(R.id.favority_recycleview)
    RecyclerView mRecyclerView;
    @BindView(R.id.no_favorites)
    TextView noFavorites;

    FavoriteAdapter mFavoriteAdapter;
    private Realm realm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this,v);
        realm = Realm.getDefaultInstance();

        final RealmResults<Favorites> results = realm.where(Favorites.class).findAll();
        if(results.size()>0){
            noFavorites.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            initRecyclerView(results);
        }else {
            noFavorites.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
        return v;
    }

    private void initRecyclerView(RealmResults<Favorites> results) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFavoriteAdapter=new FavoriteAdapter(results);
        mRecyclerView.setAdapter(mFavoriteAdapter);

    }

}
