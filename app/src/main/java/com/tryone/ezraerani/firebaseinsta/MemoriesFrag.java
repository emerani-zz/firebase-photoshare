package com.tryone.ezraerani.firebaseinsta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ezraerani on 7/25/16.
 */
public class MemoriesFrag extends Fragment implements DataHandler.OnMemoriesLoadedListener {

    @Bind(R.id.recycleMemories)
    RecyclerView recyclerView;

    DataHandler dataHandler;

    View thisView;
    RecyclerViewAdapter adapter;
    ArrayList<PhotoItem> photoItems;

    public static MemoriesFrag newInstance() {

        Bundle args = new Bundle();

        MemoriesFrag fragment = new MemoriesFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dataHandler = DataHandler.getInstance(this);
        thisView = inflater.inflate(R.layout.memories_frag, container, false);
        ButterKnife.bind(this, thisView);

        photoItems = new ArrayList<>();
        adapter = new RecyclerViewAdapter(getActivity(), photoItems);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false));


        return thisView;
    }

    @Override
    public void initAdapter() {
        photoItems.clear();
        photoItems.addAll(dataHandler.getPhotos());
        adapter.notifyDataSetChanged();

    }
}
