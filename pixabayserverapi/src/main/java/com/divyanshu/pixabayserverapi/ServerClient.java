package com.divyanshu.pixabayserverapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by divyanshunegi on 4/6/17.
 * Project : PixabaySampleApp
 */

public class ServerClient {

    private API api;

    public ServerClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.PIXABAY_API_LINK)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);
    }

    public API getApi()
    {
        return api;
    }

}
