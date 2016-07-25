package com.tryone.ezraerani.firebaseinsta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.Bind;

public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.imgPreview)
    ImageView imageView;

    @Bind(R.id.newCommentET)
    EditText newComment;

    @Bind(R.id.imageCommentsRV)
    RecyclerView recyclerView;

    CommentsRecyclerViewAdapter adapter;
    DataHandler dataHandler;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new CommentsRecyclerViewAdapter(this);
    }
}
