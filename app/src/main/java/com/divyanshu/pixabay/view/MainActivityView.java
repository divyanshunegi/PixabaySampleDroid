package com.divyanshu.pixabay.view;

import com.divyanshu.pixabayserverapi.callback.PixabayDataObject;

import java.util.List;

/**
 * Created by divyanshunegi on 4/6/17.
 * Project : PixabaySampleApp
 */
public interface MainActivityView {
    void showErrorMessage(int messageId);
    void showServerError(String message);
    void onImageListResponse(List<PixabayDataObject.Hits> imageList);
    void searchingImage();
}
