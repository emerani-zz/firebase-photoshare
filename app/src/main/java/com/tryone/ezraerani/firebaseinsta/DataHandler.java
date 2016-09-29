package com.tryone.ezraerani.firebaseinsta;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.MutableData;
import com.firebase.client.Transaction;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ezraerani on 7/25/16.
 */
public class DataHandler {

    public interface OnMemoriesLoadedListener extends GenericListener {
        void initAdapter();
    }

    public interface OnSelectedImageCommentsLoadedListener extends GenericListener {
        void loadImageComments();
    }

    private static GenericListener myListener;
    private static DataHandler instance = new DataHandler();
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    private PhotoItem selectedPhotoItem = null;
    private float selectedPhotoItemAspectRatio;
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
//        getPhotosFromDb();
    }

    public static DataHandler getInstance() {
        return instance;
    }

    public static DataHandler getInstance(GenericListener listener) {
        myListener = listener;
        return instance;
    }

    public void setSelectedPhotoItem(int position) {
        selectedPhotoItem = photos.get(position);
        Log.d("selectedPhoto", "" + selectedPhotoItem.getComments().size());
    }

    public PhotoItem getSelectedPhotoItem() {
        return selectedPhotoItem;
    }

    public void uploadCapturedPhoto(Uri uri) {

        String timeStamp = getTimeStamp();
        StorageReference imageRef = storageReference.child(timeStamp);

        UploadTask uploadTask = imageRef.putFile(uri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d("success", "called");
                updateDb(taskSnapshot.getDownloadUrl().toString());
//                postRef.child("download_url").setValue(taskSnapshot.getDownloadUrl().toString());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("failure", e.getMessage());
            }
        });

    }

    private void updateDb(String url) {
        final Firebase postRef = myFirebaseRef.child("photos").push();
        final String key = postRef.getKey();
//        final Firebase keyRef = postRef.child("photo_key");
//        keyRef.setValue(key);
        PhotoItem photoItem = new PhotoItem();
        photoItem.setPhoto_key(key);
        photoItem.setDownload_url(url);
        postRef.setValue(photoItem);
        Log.d("updatedDb", "");
    }

    private void uploadSelectedPhoto() {
    }

    public void getPhotosFromDb() {
        Log.d("getPhotosFromDb", "called");
        Query getPics = myDB.child("photos");
        getPics.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("dataSnapshot", "" + dataSnapshot.getChildrenCount());

                if (dataSnapshot.getChildrenCount() > 0) {
                    Log.d("shot", "thing");

                    photos.clear();

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

        final Firebase commentRef = myFirebaseRef.child("photos").child(selectedPhotoItem.getPhoto_key())
                .child("comments").push();
        commentRef.setValue(comment);


    }

    private String getTimeStamp() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

    public ArrayList<PhotoItem> getPhotos() {
        return photos;
    }

    public void addLike() {
        Log.d("addLike", "called");

        Firebase addLikeRef = myFirebaseRef.child("photos")
                .child(selectedPhotoItem.getPhoto_key()).child("likes");
        addLikeRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Log.d("doTransaction", "called");

                if (mutableData.getValue() == null) {
                    mutableData.setValue(1);
                } else {

                    mutableData.setValue((Long) mutableData.getValue() + 1);
                }

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(FirebaseError firebaseError, boolean b, com.firebase.client.DataSnapshot dataSnapshot) {
//                Log.d("onComplete", firebaseError.getMessage());

            }
        });
    }


    public void loadComments() {
        Query commentsQuery = myDB.child("photos/" + selectedPhotoItem.getPhoto_key() + "/comments");
        commentsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, String> comments = (HashMap<String, String>) dataSnapshot.getValue();
                selectedPhotoItem.setComments(comments);
                ((OnSelectedImageCommentsLoadedListener) myListener).loadImageComments();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
