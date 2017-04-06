package com.divyanshu.pixabay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.divyanshu.pixabayserverapi.BuildConfig;
import com.divyanshu.pixabayserverapi.ServerClient;
import com.divyanshu.pixabayserverapi.callback.PixabayDataObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.tag(MainActivity.class.getSimpleName());
        final TextView tv = (TextView) findViewById(R.id.test);

        new ServerClient().getApi().fetchPixabayImages(BuildConfig.PIXABAY_API_KEY,"sunset").enqueue(new Callback<PixabayDataObject>() {
            @Override
            public void onResponse(Call<PixabayDataObject> call, Response<PixabayDataObject> response) {
                Timber.i(response.body().hits.get(0).previewURL);
                String str = "";
                for(PixabayDataObject.Hits hits: response.body().hits){
                    str+=hits.previewURL+"\n";
                }
                tv.setText(str);
            }

            @Override
            public void onFailure(Call<PixabayDataObject> call, Throwable t) {
                Timber.e(t.getMessage());
            }
        });
    }
}
