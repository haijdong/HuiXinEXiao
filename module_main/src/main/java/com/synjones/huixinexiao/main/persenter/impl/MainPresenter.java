package com.synjones.huixinexiao.main.persenter.impl;


import com.synjones.huixinexiao.common_base.mvp.BasePresenter;
import com.synjones.huixinexiao.main.model.MainModel;
import com.synjones.huixinexiao.main.persenter.MainContract;

/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class MainPresenter extends BasePresenter<MainContract.Model,MainContract.View> {

    @Override
    protected MainContract.Model createModel() {
        return new MainModel();
    }

    public void requestData() {

    }



}
