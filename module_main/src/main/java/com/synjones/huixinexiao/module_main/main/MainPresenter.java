package com.synjones.huixinexiao.module_main.main;


import com.synjones.huixinexiao.common_base.mvp.BasePresenter;

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
