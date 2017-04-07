package com.divyanshu.pixabay;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.divyanshu.pixabayserverapi.callback.PixabayDataObject;

import java.util.List;

/**
 * Created by divyanshunegi on 4/6/17.
 * Project : PixabaySampleApp
 */
public class PixabayListAdapter extends
        RecyclerView.Adapter<PixabayListAdapter.ViewHolder>{

    private final Context mContext;
    private List<PixabayDataObject.Hits> mPixabayImages;

    public PixabayListAdapter(Context context, List<PixabayDataObject.Hits> mPixabayImages) {
        this.mPixabayImages = mPixabayImages;
        this.mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View elementView = inflater.inflate(R.layout.element_pixabay_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(elementView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PixabayDataObject.Hits image = mPixabayImages.get(position);

        Glide
                .with(getContext())
                .load(image.previewURL)
                .centerCrop()
                .placeholder(R.drawable.placeholder_emoji)
                .crossFade()
                .into(holder.getPixabayImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(),ImageFullScreenActivity.class);
                in.putExtra(ImageFullScreenActivity.IMAGE_LINK_KEY,image.webformatURL);
                getContext().startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPixabayImages.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView pixabayImage;

        public ViewHolder(View itemView) {
            super(itemView);
            pixabayImage = (ImageView) itemView.findViewById(R.id.elementImageView);
        }

        public ImageView getPixabayImage() {
            return pixabayImage;
        }
    }

}
