package com.tryone.ezraerani.firebaseinsta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity implements DataHandler.OnSelectedImageCommentsLoadedListener {

    @Bind(R.id.enlargedImageView)
    ImageView imageView;

    @Bind(R.id.newCommentET)
    EditText newComment;

    @Bind(R.id.imageCommentsRV)
    RecyclerView recyclerView;

    @Bind(R.id.numOfLikesTV)
    TextView numberOfLikes;


    CommentsRecyclerViewAdapter adapter;
    DataHandler dataHandler;
    Map<String, String> comments;
    Picasso picasso;
    PhotoItem thisphoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataHandler = DataHandler.getInstance();
        thisphoto = dataHandler.getSelectedPhotoItem();
        picasso.with(this).load(thisphoto.getDownload_url())
                .resize(800, 800)
                .centerInside()
                .into(imageView);


        comments = thisphoto.getComments();
        numberOfLikes.setText(getString(R.string.num_likes) + " " + Integer.toString(thisphoto.getLikes()));

        Log.d("commentsSize", "" + comments.size());
        adapter = new CommentsRecyclerViewAdapter(this, comments);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));

    }

    @OnClick(R.id.newCommentSaveButton)
    public void postNewComment(View view) {
        String comment = newComment.getText().toString();
        dataHandler.postComment(comment);
        newComment.setText("");
    }

    @OnClick(R.id.likeButton)
    public void addLike(View view) {
        Log.d("heartButton", "hearted");
        dataHandler.addLike();

    }


    @Override
    public void loadImageComments() {
        comments = dataHandler.getSelectedPhotoItem().getComments();
        adapter.notifyDataSetChanged();
        Log.d("loadImage", "comments");
    }
}
