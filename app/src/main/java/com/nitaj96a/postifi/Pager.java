package com.nitaj96a.postifi;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by n on 5/26/2018.
 */

public class Pager extends FragmentStatePagerAdapter {

    int tabCount;

    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabPostInfo tbpi = new TabPostInfo();
                return tbpi;
            case 1:
                TabComments tbc = new TabComments();
                return tbc;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
