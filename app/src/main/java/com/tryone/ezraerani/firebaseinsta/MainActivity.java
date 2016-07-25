package com.tryone.ezraerani.firebaseinsta;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    Firebase myFirebaseRef;
    PagerAdapter pagerAdapter;
    ArrayList<Fragment> frags;
    ArrayList<String> names;

    @Bind(R.id.viewpager)
    ViewPager viewPager;

    @Bind(R.id.tabs)
    TabLayout tabLayout;





    // TODO: 7/25/16 set up a viewpager to toggle between "moments" and take/upload option


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);

        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://cameracloud-19e48.firebaseio.com/");
        ButterKnife.bind(this);

        setUpPaging();




    }

    public void setUpPaging() {

        MemoriesFrag memoriesFrag = MemoriesFrag.newInstance();
        TakeOrSelectFrag takeOrSelectFrag = TakeOrSelectFrag.newInstance();

        frags = new ArrayList<>();
        frags.add(memoriesFrag);
        frags.add(takeOrSelectFrag);

        names = new ArrayList<>();
        names.add("Moments");
        names.add("Add Photo");

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), names, frags);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

}
