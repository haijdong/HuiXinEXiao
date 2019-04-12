package com.synjones.huixinexiao.module_user;

import android.view.View;

import com.synjones.huixinexiao.common_base.app.IViewDelegate;
import com.synjones.huixinexiao.common_base.base.BaseFragment;

import androidx.annotation.Keep;

/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
@Keep
public class MyViewDelegate implements IViewDelegate {

    @Override
    public BaseFragment getFragment(String name) {
        return UserFragment.getInstance();
    }

    @Override
    public View getView(String name) {
        return null;
    }
}
