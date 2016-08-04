package com.tryone.ezraerani.firebaseinsta;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by ezraerani on 8/2/16.
 */
public class BaneOfMyExistence extends Application {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);


//        mAuth = FirebaseAuth.getInstance();
//
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User is signed in
//                    Log.d("tag", "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    // User is signed out
//                    Log.d("tag", "onAuthStateChanged:signed_out");
//                }
//
//            }
//        };
//
//        mAuth.signInAnonymously();

    }


}
