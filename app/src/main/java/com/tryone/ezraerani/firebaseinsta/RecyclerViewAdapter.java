package com.tryone.ezraerani.firebaseinsta;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ezraerani on 7/25/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MemoryHolder> {


    public RecyclerViewAdapter() {
    }

    @Override
    public MemoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.indiv_memory, parent, false);

        return new MemoryHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MemoryHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class MemoryHolder extends RecyclerView.ViewHolder {

        public MemoryHolder(View itemView) {
            super(itemView);

        }
    }

}




