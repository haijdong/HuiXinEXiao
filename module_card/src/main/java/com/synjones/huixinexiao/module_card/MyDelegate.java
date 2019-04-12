package com.synjones.huixinexiao.module_card;


import com.orhanobut.logger.Logger;
import com.synjones.huixinexiao.common_base.app.IApplicationDelegate;
import com.synjones.huixinexiao.common_base.app.ViewManager;

import androidx.annotation.Keep;

/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
@Keep
public class MyDelegate implements IApplicationDelegate {

    @Override
    public void onCreate() {
        //主动添加
        ViewManager.getInstance().addFragment(0, CardFragment.getInstance());
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }
}
