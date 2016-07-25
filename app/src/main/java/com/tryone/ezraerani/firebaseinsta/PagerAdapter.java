package com.tryone.ezraerani.firebaseinsta;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by ezraerani on 7/25/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {

    List<String> names;
    List<Fragment> frags;

    public PagerAdapter(FragmentManager fm, List<String> names, List<Fragment> frags) {
        super(fm);
        this.frags = frags;
        this.names = names;

    }

    @Override
    public Fragment getItem(int position) {
        return frags.get(position);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return names.get(position);
    }
}
