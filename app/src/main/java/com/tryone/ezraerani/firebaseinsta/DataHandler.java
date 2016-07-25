package com.tryone.ezraerani.firebaseinsta;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by ezraerani on 7/25/16.
 */
public class DataHandler {

    private static DataHandler instance = new DataHandler();
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    private PhotoItem selectedPhotoItem = null;
    private ArrayList<PhotoItem> photos = null;

    private DataHandler() {
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReferenceFromUrl("gs://cameracloud-19e48.appspot.com");
    }

    public static DataHandler getInstance() {
        return instance;
    }

    public void setSelectedPhotoItem(int position) {
        selectedPhotoItem = photos.get(position);
    }

    public PhotoItem getSelectedPhotoItem() {
        return selectedPhotoItem;
    }

    public void uploadCapturedPhoto(){}

    public void uploadSelectedPhoto(){}

    public ArrayList<PhotoItem> getPhotosFromDb() {

        return photos;
    }

    public void deletePhoto(PhotoItem photoItem){}

    public void postComment(String comment){}
}
