package com.example.panagiotis.skylarapiex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.panagiotis.skylarapiex.adapter.HomeAdapter;
import com.example.panagiotis.skylarapiex.api.ApiCall;
import com.example.panagiotis.skylarapiex.api.Connection;
import com.example.panagiotis.skylarapiex.model.SkylarkPojo;
import com.example.panagiotis.skylarapiex.model.pojo.EpidoseDetails;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomeFragment extends Fragment {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;


    ApiCall mApiCall;
    HomeAdapter mHomeAdapter;
    ArrayList<String> mUrlEpisodeList;
    ArrayList<EpidoseDetails> mEpidoseDetailsArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,v);
        mUrlEpisodeList=new ArrayList<>();
        mEpidoseDetailsArrayList=new ArrayList<>();
        makeHomeApiCall();
        return v;
    }

    private void ulrCall() {
        for (String url :mUrlEpisodeList){
            makeEpisodeApiCall(url);
        }
//        makeEpisodeApiCall("/api/episodes/film_5f4472c8afb645a7b94754dc0cb8812e/");

    }

    public void makeEpisodeApiCall(String url){
        Constants.EPISODE_URL=url.substring(1);
        mApiCall = Connection.getEpisodeConnection();
        mApiCall.getEpisode(Constants.BASE_URL+url.substring(1))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EpidoseDetails>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(EpidoseDetails epidoseDetails) {
                        mEpidoseDetailsArrayList.add(epidoseDetails);
                        initRecyclerView(mEpidoseDetailsArrayList);
                    }
                });
    }

    public void makeHomeApiCall() {
        mApiCall = Connection.getHomeConnection();
        mApiCall.getApi("home")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SkylarkPojo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SkylarkPojo skylarkPojo) {
                        for(int i=0;i<skylarkPojo.getObjects().get(0).getItems().size();i++){
                            mUrlEpisodeList.add(skylarkPojo.getObjects().get(0).getItems().get(i).getContentUrl());
                        }
                        ulrCall();
                    }
                });
    }


    private void initRecyclerView(ArrayList<EpidoseDetails> mEpidoseDetailsArrayList) {
        mHomeAdapter = new HomeAdapter(mEpidoseDetailsArrayList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mHomeAdapter);
    }

}
