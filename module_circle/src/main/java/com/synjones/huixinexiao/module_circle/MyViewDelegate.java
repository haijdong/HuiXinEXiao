package com.synjones.huixinexiao.module_circle;

import android.view.View;

import com.synjones.huixinexiao.common_base.app.IViewDelegate;
import com.synjones.huixinexiao.common_base.base.BaseFragment;

import androidx.annotation.Keep;

/**
 * <p>类说明</p>
 *
 * @author 张华洋 2018/1/4 22:16
 * @version V2.8.3
 * @name MyViewDelegate
 */
@Keep
public class MyViewDelegate implements IViewDelegate {

    @Override
    public BaseFragment getFragment(String name) {
        return CircleFragment.getInstance();
    }

    @Override
    public View getView(String name) {
        return null;
    }
}
