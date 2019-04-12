package com.synjones.huixinexiao.module_school;



import com.synjones.huixinexiao.common_base.mvp.BasePresenter;


/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class SchoolPresenter extends BasePresenter<SchoolContract.Model, SchoolContract.View> {
    @Override
    protected SchoolContract.Model createModel() {
        return new SchoolModel();
    }


    public void requestData(){

    }
}
