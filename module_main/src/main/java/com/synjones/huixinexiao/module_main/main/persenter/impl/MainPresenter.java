package com.synjones.huixinexiao.module_main.main.persenter.impl;


import com.synjones.huixinexiao.common_base.mvp.BasePresenter;
import com.synjones.huixinexiao.module_main.main.model.MainModel;
import com.synjones.huixinexiao.module_main.main.persenter.MainContract;

/**
 * author  :  donghaijun
 * data    :  2019/3/13
 * version :  1.0
 * des     :
 */
public class MainPresenter extends BasePresenter<MainContract.Model,MainContract.View> {

    @Override
    protected MainContract.Model createModel() {
        return new MainModel();
    }

    public void requestData() {

    }



}
