package com.tryone.ezraerani.firebaseinsta;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ezraerani on 7/25/16.
 */
public class TakeOrSelectFrag extends Fragment {

    View thisView;
    private Uri fileUri;

    @Bind(R.id.imgPreview)
    ImageView imgPreview;

    private DataHandler handler;

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

        File output = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath(), "/myimage.jpg");

//        String outputFilePath = Environment.getExternalStorageDirectory()
//                .getAbsolutePath() + "/myimage.jpg";
        fileUri = Uri.fromFile(output);

        handler = DataHandler.getInstance();

        return thisView;
    }

    @OnClick({R.id.selectPhotoButton, R.id.takePhotoButton})
    public void takeOrSelectPhoto(View view) {

        Intent activityToStart = null;
        int requestCode = 0;

        switch (view.getId()) {
            case R.id.takePhotoButton:
                activityToStart = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityToStart.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
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

        Uri thisUri = null;

        if (requestCode == 1001) {
            thisUri = fileUri;

        } else if (requestCode == 1002) {

            thisUri = data.getData();
        }
        handler.uploadCapturedPhoto(thisUri);
    }
}
