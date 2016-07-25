package com.tryone.ezraerani.firebaseinsta;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;

/**
 * Created by ezraerani on 7/25/16.
 */
public class MemoriesFrag extends Fragment {

    @Bind(R.id.recycleMemories)
    RecyclerView recyclerView;
    View thisView;

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

        thisView = inflater.inflate(R.layout.memories_frag, container, false);

        return thisView;
    }
}
