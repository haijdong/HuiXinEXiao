package com.synjones.huixinexiao;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.synjones.common_base.app.BaseApplication;
import com.synjones.common_base.utils.AppUtils;
import com.synjones.common_base.utils.CommonUtils;

import androidx.multidex.MultiDex;
import butterknife.internal.Utils;


/**
 * @author xuhao
 * @date 2018/6/10 16:20
 * @desc
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
