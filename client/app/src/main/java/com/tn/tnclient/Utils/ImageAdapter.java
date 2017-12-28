package com.tn.tnclient.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tn.tnclient.R;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alimjan on 11/16/17.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.FileViewHolder> {

    private final ArrayList<String> paths;
    private final Context context;
    private int imageSize;

    public ImageAdapter(Context context, ArrayList<String> paths)
    {
        this.context = context;
        this.paths = paths;
        setColumnNumber(context,3);
    }

    private void setColumnNumber(Context context, int columnNum) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        imageSize = widthPixels / columnNum;
    }

    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_image_uploader, parent, false);

        return new FileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FileViewHolder holder, int position) {
        String path = paths.get(position);
        Glide.with(context).load(new File(path))
                .apply(RequestOptions
                        .centerCropTransform()
                        .dontAnimate()
                        .override(imageSize, imageSize)
                        .placeholder(droidninja.filepicker.R.drawable.image_placeholder))
                .thumbnail(0.5f)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return paths.size();
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_profile_photo)
        ImageView imageView;

        public FileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
