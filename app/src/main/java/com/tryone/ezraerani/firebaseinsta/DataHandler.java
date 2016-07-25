package com.tryone.ezraerani.firebaseinsta;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by ezraerani on 7/25/16.
 */
public class DataHandler {

    private DataHandler instance = new DataHandler();
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    private DataHandler() {
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReferenceFromUrl("gs://cameracloud-19e48.appspot.com");
    }

    public DataHandler getInstance() {
        return instance;
    }

    public void uploadCapturedPhoto(){}

    public void uploadSelectedPhoto(){}

    public ArrayList<PhotoItem> getPhotosFromDb() {
        return new ArrayList<>();
    }

    public void deletePhoto(PhotoItem photoItem){}
}
