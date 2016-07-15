package me.lynnchurch.mysample.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import me.lynnchurch.mysample.R;
import me.lynnchurch.mysample.adapter.StringsAdapter;

public class CollapsingToolbarLayoutActivity extends AppCompatActivity
{
    private List<String> mItems=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_toolbar_layout);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        RecyclerView rv_items=(RecyclerView)findViewById(R.id.rv_items);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getStringArray(R.array.features)[1]);

        for (int i = 0; i <50 ; i++)
        {
            mItems.add("item"+i);
        }
        StringsAdapter mAdapter=new StringsAdapter(mItems);
        rv_items.setAdapter(mAdapter);
        rv_items.setLayoutManager(new LinearLayoutManager(this));

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
