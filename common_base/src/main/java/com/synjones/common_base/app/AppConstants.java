package com.synjones.common_base.app;

import com.synjones.common_base.utils.Utils;

import java.io.File;

/**
 * author  :  donghaijun
 * data    :  2019/3/8
 * version :  1.0
 * des     :  app 常量
 */
public class AppConstants {

    /**
     * Path
     */
    public static final String PATH_DATA = Utils.getContext().getApplicationContext().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    static final String BUGLY_ID = "16e54f8921";
}
