package com.synjones.module_main.main;



import com.synjones.common_base.base.BaseFragment;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

/**
 * <p>Fragments适配器 </p>
 *
 * @author 张华洋 2017/9/27 10:14
 * @version V1.1
 * @name ResourcePagerAdapter
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> mFragments;

    public FragmentAdapter(FragmentManager fm, List<BaseFragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments != null ? mFragments.size() : 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
