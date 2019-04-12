package com.synjones.huixinexiao.main.model;


import com.synjones.huixinexiao.common_base.mvp.BaseModel;
import com.synjones.huixinexiao.common_base.net.BaseHttpResult;
import com.synjones.huixinexiao.main.data.entity.TestNews;
import com.synjones.huixinexiao.main.data.repository.RetrofitUtils;
import com.synjones.huixinexiao.main.persenter.MainContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class MainModel extends BaseModel implements MainContract.Model {

    @Override
    public Observable<BaseHttpResult<List<TestNews>>> getGankData() {
        return RetrofitUtils.getHttpService().getGankData();
    }
}
