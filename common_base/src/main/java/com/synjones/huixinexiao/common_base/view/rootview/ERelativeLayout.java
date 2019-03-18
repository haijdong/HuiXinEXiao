package com.synjones.huixinexiao.common_base.view.rootview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.synjones.huixinexiao.common_base.R;

import androidx.annotation.Nullable;

/**
 * author: donghaijun
 * -----------------
 * data:   2019/1/23
 */
public class ERelativeLayout extends RelativeLayout {

    public ERelativeLayout(Context context) {
        super(context);
        init();
    }

    public ERelativeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ERelativeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        setBackgroundColor(getResources().getColor(R.color.theme));
    }




}
