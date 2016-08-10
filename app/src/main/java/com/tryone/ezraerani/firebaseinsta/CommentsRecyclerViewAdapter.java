package com.tryone.ezraerani.firebaseinsta;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ezraerani on 7/25/16.
 */
public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentsRecyclerViewAdapter.CommentHolder> {

    private Context context;
    private DataHandler dataHandler;
    ArrayList<String> comments;


    public CommentsRecyclerViewAdapter(Context context, Map<String, String> comments) {
        this.context = context;
        this.comments = new ArrayList<>();
        this.comments.addAll(comments.values());
        Log.d("ikiki", ""+comments.size());
        dataHandler = DataHandler.getInstance();
//        this.comments = dataHandler.getSelectedPhotoItem().getComments();
    }

    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.indiv_comment, parent, false);

        return new CommentHolder(itemView);

    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {
        holder.indivComment.setText(comments.get(position));
    }

    @Override
    public int getItemCount() {

//        int size = 0;
//        if (comments != null) {
//            size = comments.size();
//        }
        Log.d("commentssize", ""+comments.size());
        return comments.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.indivCommentTV)
        TextView indivComment;

        public CommentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
