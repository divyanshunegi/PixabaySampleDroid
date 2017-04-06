package com.divyanshu.pixabayserverapi;

import com.divyanshu.pixabayserverapi.callback.PixabayDataObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by divyanshunegi on 4/6/17.
 * Project : PixabaySampleApp
 */
public interface API {

    @GET("api/")
    Call<PixabayDataObject> fetchPixabayImages(@Query("key") String apiKey, @Query("q") String searchQuerry);

}
