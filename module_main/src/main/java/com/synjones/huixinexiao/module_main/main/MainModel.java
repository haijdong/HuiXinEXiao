package com.synjones.huixinexiao.module_main.main;


import com.synjones.huixinexiao.common_base.mvp.BaseModel;
import com.synjones.huixinexiao.common_base.net.BaseHttpResult;
import com.synjones.huixinexiao.module_main.data.entity.TestNews;
import com.synjones.huixinexiao.module_main.data.repository.RetrofitUtils;

import java.util.List;

import io.reactivex.Observable;

/**
 * author  :  donghaijun
 * data    :  2019/3/13
 * version :  1.0
 * des     :
 */
public class MainModel extends BaseModel implements MainContract.Model{

    @Override
    public Observable<BaseHttpResult<List<TestNews>>> getGankData() {
        return RetrofitUtils.getHttpService().getGankData();
    }
}
