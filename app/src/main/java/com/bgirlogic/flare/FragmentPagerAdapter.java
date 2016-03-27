package com.bgirlogic.flare;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by kimsuh on 3/26/16.
 */
public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Home", "Favorites"};

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return HomeFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
