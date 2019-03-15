package com.synjones.common_base.app;


import android.view.View;

import com.synjones.common_base.base.BaseFragment;

import androidx.annotation.Keep;

/**
 * author  :  donghaijun
 * data    :  2019/3/15
 * version :  1.0
 * des     :  APP 的配置参数
 */
@Keep
public interface IViewDelegate {

    BaseFragment getFragment(String name);

    View getView(String name);

}
