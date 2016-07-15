package me.lynnchurch.mysample.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import me.lynnchurch.mysample.R;
import me.lynnchurch.mysample.adapter.TabPagerAdapter;
import me.lynnchurch.mysample.fragment.ViewPagerFragment;

public class TabLayoutActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        ViewPager viewPager=(ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getStringArray(R.array.features)[0]);
        List<String> titles=new ArrayList<>();
        List<Fragment> fragments=new ArrayList<>();
        for (int i = 0; i <9 ; i++)
        {
            String title="Tab"+(i+1);
            titles.add(title);
            fragments.add(ViewPagerFragment.newInstance(title));
        }
        FragmentStatePagerAdapter adapter=new TabPagerAdapter(getSupportFragmentManager(),titles,fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
