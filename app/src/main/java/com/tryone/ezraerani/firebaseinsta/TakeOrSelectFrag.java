package com.tryone.ezraerani.firebaseinsta;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ezraerani on 7/25/16.
 */
public class TakeOrSelectFrag extends Fragment {

    View thisView;

    @Bind(R.id.imgPreview)
    ImageView imgPreview;

    public static TakeOrSelectFrag newInstance() {
        
        Bundle args = new Bundle();
        
        TakeOrSelectFrag fragment = new TakeOrSelectFrag();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        thisView = inflater.inflate(R.layout.takeselect_frag, container, false);

        ButterKnife.bind(this, thisView);

        return thisView;
    }

    @OnClick({R.id.selectPhotoButton, R.id.takePhotoButton})
    public void takeOrSelectPhoto(View view) {

        Intent activityToStart = null;
        int requestCode = 0;

        switch (view.getId()) {
            case R.id.takePhotoButton:
                activityToStart = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                requestCode = 1001;
                break;
            case R.id.selectPhotoButton:
                activityToStart = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                requestCode = 1002;
                break;
            default:
                break;
        }

        startActivityForResult(activityToStart, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
