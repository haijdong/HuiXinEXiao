package com.synjones.huixinexiao;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.synjones.huixinexiao.common_base.app.BaseApplication;
import com.synjones.huixinexiao.common_base.utils.AppUtils;
import com.synjones.huixinexiao.common_base.utils.CommonUtils;

import androidx.multidex.MultiDex;


/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class App extends BaseApplication {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (AppUtils.isAppDebug(this)) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);

        //Bugly
        initBugly();
    }



    private void initBugly() {
        // 获取当前包名
        String packageName = getApplicationContext().getPackageName();
        // 获取当前进程名
        String processName = CommonUtils.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
//        strategy.setUploadProcess(processName == null || processName.equals(packageName));
//        CrashReport.initCrashReport(getApplicationContext(), MyConstants.BUGLY_ID, false, strategy);
    }

}
