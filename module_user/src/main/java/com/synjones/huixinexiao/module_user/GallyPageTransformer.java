package com.synjones.huixinexiao.module_user;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by donghaijun on 2018/1/23.
 */

public class GallyPageTransformer implements ViewPager.PageTransformer {
    private static final float min_scale = 0.7f;

    @Override
    public void transformPage(View page, float position) {
        float scaleFactor = Math.max(min_scale, 1 - Math.abs(position));
        float rotate = 20 * Math.abs(position);
        if (position < -1) {
        } else if (position < 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
//            page.setRotationY(-rotate);
        } else if (position >= 0 && position < 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
//            page.setRotationY(rotate);
        } else if (position >= 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
//            page.setRotationY(rotate);
        }
    }
}