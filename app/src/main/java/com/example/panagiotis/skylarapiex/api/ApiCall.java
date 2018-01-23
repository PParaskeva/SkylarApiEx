package com.example.panagiotis.skylarapiex.api;

import com.example.panagiotis.skylarapiex.Constants;
import com.example.panagiotis.skylarapiex.model.SkylarkPojo;
import com.example.panagiotis.skylarapiex.model.pojo.EpidoseDetails;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Panagiotis on 22/01/2018.
 */

public interface ApiCall {

    @GET(Constants.EXTENED_URL)
    Observable<SkylarkPojo> getApi(
            @Query("slug") String slug
    );

    @GET
    Observable<EpidoseDetails> getEpisode(
            @Url String url
    );
}
