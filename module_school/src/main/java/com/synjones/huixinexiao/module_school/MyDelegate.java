package com.synjones.huixinexiao.module_school;


import com.orhanobut.logger.Logger;
import com.synjones.huixinexiao.common_base.app.IApplicationDelegate;
import com.synjones.huixinexiao.common_base.app.ViewManager;

import androidx.annotation.Keep;

/**
 * <p>类说明</p>
 *
 * @author 张华洋 2017/9/20 22:29
 * @version V2.8.3
 * @name MyDelegate
 */
@Keep
public class MyDelegate implements IApplicationDelegate {

    @Override
    public void onCreate() {
        //主动添加
        ViewManager.getInstance().addFragment(0, SchoolFragment.getInstance());
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
