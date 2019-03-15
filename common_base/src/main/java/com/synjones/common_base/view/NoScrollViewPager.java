package com.synjones.common_base.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * author  :  donghaijun
 * data    :  2019/3/15
 * version :  1.0
 * des     :  $des$禁止滑动翻页的ViewPager
 */
public class NoScrollViewPager extends ViewPager {
    private boolean isPagingEnabled = true;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagerEnabled(boolean b) {
        this.isPagingEnabled = b;
    }
}
