package com.divyanshu.pixabay.presenter;

import com.divyanshu.pixabay.R;
import com.divyanshu.pixabay.view.MainActivityView;
import com.divyanshu.pixabayserverapi.BuildConfig;
import com.divyanshu.pixabayserverapi.ServerClient;
import com.divyanshu.pixabayserverapi.callback.PixabayDataObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by divyanshunegi on 4/6/17.
 * Project : PixabaySampleApp
 */
public class MainActivityPresenter {

    private MainActivityView mainActivityView;
    private ServerClient mServerClient;

    public MainActivityPresenter(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
        mServerClient = new ServerClient();
    }

    public void fetchImages(String keyword) {

        mainActivityView.searchingImage();

        if(keyword.length()<2){
            mainActivityView.showErrorMessage(R.string.short_keyword_error);
        }

        mServerClient.getApi().fetchPixabayImages(BuildConfig.PIXABAY_API_KEY,keyword).enqueue(new Callback<PixabayDataObject>() {
            @Override
            public void onResponse(Call<PixabayDataObject> call, Response<PixabayDataObject> response) {

                if(response.body()==null && response.body().hits.size()==0){
                    mainActivityView.showErrorMessage(R.string.no_image_error);
                    return;
                }
                mainActivityView.onImageListResponse(response.body().hits);
            }

            @Override
            public void onFailure(Call<PixabayDataObject> call, Throwable t) {
                Timber.e(t.getMessage());
                mainActivityView.showServerError(t.getMessage());
            }
        });
    }
}
