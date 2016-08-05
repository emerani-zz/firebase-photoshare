package com.tryone.ezraerani.firebaseinsta;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ezraerani on 7/25/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MemoryHolder> {

    private ArrayList<PhotoItem> photos;
    private Context context;
    Picasso picasso;
    DataHandler dataHandler;

    public RecyclerViewAdapter(Context context, ArrayList<PhotoItem> photos) {
        this.photos = photos;
        this.context = context;
        this.picasso = Picasso.with(context);
        dataHandler = DataHandler.getInstance();
    }

    @Override
    public MemoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.indiv_memory, parent, false);

        return new MemoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MemoryHolder holder, final int position) {

        PhotoItem photoItem = photos.get(position);
        picasso.load(photoItem.getDownload_url())
                .resize(100, 100).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataHandler.setSelectedPhotoItem(position);
                context.startActivity(new Intent(context, DetailActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class MemoryHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.memory)
        ImageView imageView;

        public MemoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}




