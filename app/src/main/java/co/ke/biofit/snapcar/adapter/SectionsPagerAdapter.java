package co.ke.biofit.snapcar.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import co.ke.biofit.snapcar.actvity.MainActivity;

public class SectionsPagerAdapter extends PagerAdapter {
    public SectionsPagerAdapter(MainActivity mainActivity, FragmentManager supportFragmentManager, Fragment[] mFragments) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }
}
