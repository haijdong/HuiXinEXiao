package com.synjones.huixinexiao.common_base.view.rootview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.synjones.huixinexiao.common_base.R;

import androidx.annotation.Nullable;

/**
 * author: donghaijun
 * -----------------
 * data:   2019/1/23
 */
public class ELinearLayout extends LinearLayout {

    public ELinearLayout(Context context) {
        super(context);
        init();
    }

    public ELinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ELinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
        setBackgroundColor(getResources().getColor(R.color.theme));
    }




}
