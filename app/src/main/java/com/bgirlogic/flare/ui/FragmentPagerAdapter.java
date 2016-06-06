package com.bgirlogic.flare.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

/**
 * Created by kimsuh on 3/26/16.
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private static final String TAG = FragmentPagerAdapter.class.getSimpleName();

    private String tabTitles[] = new String[]{"Home"};

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "position is: "+position);
        return HomeFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
