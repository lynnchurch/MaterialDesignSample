package me.lynnchurch.mysample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Lynn on 2016-7-6.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter
{
    private List<String> mTitles;
    private List<Fragment> mFragments;

    public TabPagerAdapter(FragmentManager fm,List<String> titles,List<Fragment> fragments)
    {
        super(fm);
        mTitles=titles;
        mFragments=fragments;
    }


    @Override
    public Fragment getItem(int position)
    {
        return mFragments.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mTitles.get(position);
    }
}
