package com.divyanshu.pixabay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.divyanshu.pixabay.presenter.MainActivityPresenter;
import com.divyanshu.pixabay.view.MainActivityView;
import com.divyanshu.pixabayserverapi.callback.PixabayDataObject;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

import static com.divyanshu.pixabay.R.id.infoView;

public class MainActivity extends AppCompatActivity implements MainActivityView,View.OnClickListener {

    private LinearLayout infoLayout;
    private TextView infoViewText;
    private ImageView infoViewIcon;
    private AppCompatButton infoViewButton;
    private ProgressBar infoViewProgressBar;
    private RecyclerView imageListView;

    private static String SEARCH_KEYWORD = "";
    private MainActivityPresenter mainActivityPresenter;
    private List<PixabayDataObject.Hits> mImageList = new ArrayList<>();
    private PixabayListAdapter adapter;
    private LinearLayoutManager linearManager;
    private GridLayoutManager gridLayoutManager;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.tag(MainActivity.class.getSimpleName());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        infoLayout = (LinearLayout) findViewById(infoView);
        infoViewText = (TextView) findViewById(R.id.infoViewText);
        infoViewIcon = (ImageView) findViewById(R.id.infoViewIcon);
        infoViewButton = (AppCompatButton) findViewById(R.id.infoViewButton);
        infoViewButton.setOnClickListener(this);
        infoViewProgressBar = (ProgressBar) findViewById(R.id.infoProgressBar);
        imageListView = (RecyclerView) findViewById(R.id.pixabayImageList);

        adapter = new PixabayListAdapter(this,mImageList);
        linearManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this,2);
        imageListView.setLayoutManager(gridLayoutManager);
        imageListView.setAdapter(adapter);

        mainActivityPresenter = new MainActivityPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.listLayout:
                imageListView.setLayoutManager(linearManager);
                imageListView.setAdapter(adapter);
                mMenu.findItem(R.id.listLayout).setVisible(false);
                mMenu.findItem(R.id.gridLayout).setVisible(true);
                break;
            case R.id.gridLayout:
                imageListView.setLayoutManager(gridLayoutManager);
                imageListView.setAdapter(adapter);
                mMenu.findItem(R.id.listLayout).setVisible(true);
                mMenu.findItem(R.id.gridLayout).setVisible(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showErrorMessage(int messageId) {
        infoLayout.setVisibility(View.VISIBLE);
        infoViewProgressBar.setVisibility(View.GONE);
        infoViewButton.setVisibility(View.VISIBLE);
        infoViewIcon.setImageResource(R.drawable.local_error_emoji);
        imageListView.setVisibility(View.GONE);
        infoViewText.setText(getResources().getString(messageId));
        infoViewButton.setText(getResources().getString(R.string.retry_button));
    }

    @Override
    public void showServerError(String message) {
        infoLayout.setVisibility(View.VISIBLE);
        infoViewProgressBar.setVisibility(View.GONE);
        infoViewButton.setVisibility(View.VISIBLE);
        infoViewIcon.setImageResource(R.drawable.error_emoji);
        imageListView.setVisibility(View.GONE);
        infoViewText.setText(message);
        infoViewButton.setText(getResources().getString(R.string.retry_button));
    }

    @Override
    public void onImageListResponse(List<PixabayDataObject.Hits> imageList) {
        infoLayout.setVisibility(View.GONE);
        imageListView.setVisibility(View.VISIBLE);
        mImageList.clear();
        mImageList.addAll(imageList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.infoViewButton:
                searchImagesInPixabay();
                break;
        }
    }

    private void searchImagesInPixabay() {
        infoViewButton.setVisibility(View.GONE);
        infoViewProgressBar.setVisibility(View.VISIBLE);
        infoViewText.setText(getResources().getString(R.string.search_in_progress));
        infoViewIcon.setImageResource(R.drawable.search_emoji);
        mainActivityPresenter.fetchImages(SEARCH_KEYWORD);
    }
}
