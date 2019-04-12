package com.synjones.huixinexiao.module_circle;



import com.synjones.huixinexiao.common_base.mvp.BasePresenter;



/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class CirclePresenter extends BasePresenter<CircleContract.Model, CircleContract.View> {
    @Override
    protected CircleContract.Model createModel() {
        return new CircleModel();
    }


    public void requestData(){

    }
}
