package com.example.panagiotis.skylarapiex.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.panagiotis.skylarapiex.R;
import com.example.panagiotis.skylarapiex.model.realm.CaschData;
import com.example.panagiotis.skylarapiex.model.realm.Favorites;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;


public class SingleEpisodeViewFragment extends Fragment {

    @BindView(R.id.single_view_title)
    TextView mTitle;
    @BindView(R.id.single_favorite_imageview)
    ImageView mSingleFavoriteImageView;

    private Realm realm;
    String uuid;
    String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_single_episode_view, container, false);
        ButterKnife.bind(this,v);
        uuid = getArguments().getString("uuid");
        realm = Realm.getDefaultInstance();
        final RealmResults<CaschData> results = realm.where(CaschData.class)
                .equalTo("uuid", uuid).findAll();
        isInFavorite();
        title=results.get(0).getName();
        mTitle.setText(title);
        return v;
    }

    @OnClick(R.id.single_favorite_imageview)
    public void onSingleFavoriteClick(){
        final RealmResults<Favorites> results = realm.where(Favorites.class)
                .equalTo("uuid", uuid).findAll();
        if (results.size() > 0) {

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    results.deleteFirstFromRealm();
                }
            });

            Picasso.with(getActivity())
                    .load(android.R.drawable.btn_star_big_off)
                    .into(mSingleFavoriteImageView);
        } else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Favorites favorites = realm.createObject(Favorites.class);
                    favorites.setName(title);
                    favorites.setUuid(uuid);
                }
            });
            Picasso.with(getActivity())
                    .load(android.R.drawable.btn_star_big_on)
                    .into(mSingleFavoriteImageView);
        }
    }

    public void isInFavorite() {
        final RealmResults<Favorites> favorites = realm.where(Favorites.class)
                .equalTo("uuid", uuid).findAll();
        if(favorites.size()>0){
            Picasso.with(getActivity())
                    .load(android.R.drawable.btn_star_big_on)
                    .into(mSingleFavoriteImageView);
        }else{
            Picasso.with(getActivity())
                    .load(android.R.drawable.btn_star_big_off)
                    .into(mSingleFavoriteImageView);
        }
    }
}
