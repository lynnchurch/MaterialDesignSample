package me.lynnchurch.mysample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import me.lynnchurch.mysample.R;
import me.lynnchurch.mysample.adapter.BaseAdapter;
import me.lynnchurch.mysample.adapter.StringsAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final Snackbar snackbar = Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Action", new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View view)
                            {
                            }
                        });
                snackbar.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView rv_features = (RecyclerView) findViewById(R.id.rv_features);
        List<String> featureNames=Arrays.asList(getResources().getStringArray(R.array.features));

        StringsAdapter adapter=new StringsAdapter(featureNames);
        adapter.setOnItemClickListner(new BaseAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View v, int position)
            {
                Intent intent=null;
                switch (position)
                {
                    case 0:
                        intent= new Intent(MainActivity.this, TabLayoutActivity.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, CollapsingToolbarLayoutActivity.class);
                        break;
                    default:
                }
                startActivity(intent);
            }
        });
        adapter.setOnItemLongClickListener(new BaseAdapter.OnItemLongClickListener()
        {
            @Override
            public void onItemLongClick(View v, int position)
            {
                Toast.makeText(MainActivity.this, "longClick:"+position, Toast.LENGTH_SHORT).show();
            }
        });
        rv_features.setAdapter(adapter);
        rv_features.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    //  显示菜单上图标
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu)
    {
        if (menu != null)
        {
            if (menu.getClass() == MenuBuilder.class)
            {
                try
                {
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
        } else if (id == R.id.nav_gallery)
        {

        } else if (id == R.id.nav_slideshow)
        {

        } else if (id == R.id.nav_manage)
        {

        } else if (id == R.id.nav_share)
        {

        } else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
