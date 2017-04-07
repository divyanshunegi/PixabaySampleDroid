package com.divyanshu.pixabay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alexvasilkov.gestures.views.GestureImageView;
import com.bumptech.glide.Glide;

/**
 * Created by divyanshunegi on 4/7/17.
 * Project : PixabaySampleApp
 */
public class ImageFullScreenActivity extends AppCompatActivity {

    public static String IMAGE_LINK_KEY = "image_link_key";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen_image);

        int mUIFlag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

        getWindow().getDecorView().setSystemUiVisibility(mUIFlag);

        Intent in = getIntent();
        if(in==null){
            Toast.makeText(this, getResources().getString(R.string.image_link_missing), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String imageUrl = in.getStringExtra(IMAGE_LINK_KEY);
        if(imageUrl==null) {
            Toast.makeText(this, getResources().getString(R.string.pass_image_error), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        GestureImageView fullScreenView = (GestureImageView) findViewById(R.id.fullImageView);

        Glide
                .with(this)
                .load(imageUrl)
                .placeholder(R.drawable.placeholder_emoji)
                .crossFade()
                .into(fullScreenView);

    }
}
