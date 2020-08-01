package co.ke.biofit.snapcar.actvity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import co.ke.biofit.snapcar.R;
import co.ke.biofit.snapcar.adapter.SectionsPagerAdapter;
import co.ke.biofit.snapcar.fragment.BrowseFragment;
import co.ke.biofit.snapcar.fragment.CollectionFragment;
import co.ke.biofit.snapcar.fragment.HomeFragment;
import co.ke.biofit.snapcar.fragment.OptionsFragment;
import co.ke.biofit.snapcar.util.MediaUtils;
import co.ke.biofit.snapcar.util.TabUtils;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    private static final Fragment[] mFragments = {
            new Fragment(),
            new Fragment(),
            new Fragment(),
            new Fragment()
    };

    private ArrayList<ActionBar.Tab> mTabs;

    private ViewPager mViewPager;
    private ActionBar mActionBar;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupActionBar();
    }

    private void setupActionBar() {
        mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        setupPageViewer();

        mTabs = new ArrayList<ActionBar.Tab>();
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            ActionBar.Tab t = mActionBar.newTab().setText(TabUtils.getTabTitleId(i)).setTabListener(this);
            mTabs.add(t);
            mActionBar.addTab(t);
        }
    }
    @SuppressLint("WrongViewCast")
    private void setupPageViewer() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), mFragments);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem actionSnapIt = menu.findItem(R.id.action_snap_it);
        MenuItemCompat.getActionView(actionSnapIt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(actionSnapIt);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_snap_it:
                if (MediaUtils.isExternalStorageWritable()) {
                    Intent intent = new Intent(this, CameraActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "External Storage not available.",
                            Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        TabUtils.setCurrentTabPosition(tab.getPosition());
        mViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}