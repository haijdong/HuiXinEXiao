package com.synjones.huixinexiao.module_user;


import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.PagerAdapter;

/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class CardPagerAdapter extends PagerAdapter {

    private OnItemClickListener mOnItemClickListener = null;
    /**
     * 进入详情
     */
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    private ArrayList<View> mImageViews;

    public CardPagerAdapter(List<View> mImageViews) {
        this.mImageViews = (ArrayList<View>) mImageViews;
    }

    @Override
    public int getCount() {
//        return Integer.MAX_VALUE;
        return mImageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
//        position %= mImageViews.size();

        View view = mImageViews.get(position);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onItemClick(v,position);
                }
            }
        });
        container.addView(view);
        return view;
    }

}