package com.tryone.ezraerani.firebaseinsta;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ezraerani on 7/25/16.
 */
public class DataHandler {

    public interface OnMemoriesLoadedListener extends GenericListener {
        void initAdapter();
    }

    private static GenericListener myListener;
    private static DataHandler instance = new DataHandler();
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    private PhotoItem selectedPhotoItem = null;
    private ArrayList<PhotoItem> photos;
    private final String TIME_STAMP = "timeStamp";
    Firebase myFirebaseRef;
    private DatabaseReference myDB;

    private DataHandler() {
        myFirebaseRef = new Firebase("https://cameracloud-19e48.firebaseio.com/");
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReferenceFromUrl("gs://cameracloud-19e48.appspot.com/");
        myDB = FirebaseDatabase.getInstance().getReference();
        photos = new ArrayList<>();
        getPhotosFromDb();
    }

    public static DataHandler getInstance() {
        return instance;
    }

    public static DataHandler getInstance(GenericListener listener){
        myListener = listener;
        return instance;
    }

    public void setSelectedPhotoItem(int position) {
        selectedPhotoItem = photos.get(position);
    }

    public PhotoItem getSelectedPhotoItem() {
        return selectedPhotoItem;
    }

    public void uploadCapturedPhoto(Uri uri) {

        String timeStamp = getTimeStamp();
        StorageReference imageRef = storageReference.child(timeStamp);

        StorageMetadata.Builder builder = new StorageMetadata.Builder();
        final Firebase postRef = myFirebaseRef.child("photos").push();
        final String key = postRef.getKey();
        postRef.setValue(key);

        builder.setCustomMetadata("key", key);
        UploadTask uploadTask = imageRef.putFile(uri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("success", "called");
                postRef.child("download_url").setValue(taskSnapshot.getDownloadUrl().toString());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("failure", e.getMessage());
            }
        });


    }

    private void uploadSelectedPhoto() {
    }

    private void getPhotosFromDb() {
        Log.d("getPhotosFromDb", "called");



        Query getPics = myDB.child("photos");
        getPics.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("dataSnapshot", "" + dataSnapshot.getChildrenCount());

                if (dataSnapshot.getChildrenCount() > 0) {

                    for (DataSnapshot shot : dataSnapshot.getChildren()) {
                        photos.add(shot.getValue(PhotoItem.class));
                        Log.d("url", shot.getKey());

                    }

                    ((OnMemoriesLoadedListener) myListener).initAdapter();

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void deletePhoto(PhotoItem photoItem) {
    }

    public void postComment(String comment) {
    }

    private String getTimeStamp() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

    public ArrayList<PhotoItem> getPhotos() {
        return photos;
    }
}
