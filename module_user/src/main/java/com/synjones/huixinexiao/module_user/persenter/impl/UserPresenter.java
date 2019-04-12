package com.synjones.huixinexiao.module_user.persenter.impl;



import com.synjones.huixinexiao.common_base.mvp.BasePresenter;
import com.synjones.huixinexiao.module_user.persenter.UserContract;
import com.synjones.huixinexiao.module_user.model.UserModel;


/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class UserPresenter extends BasePresenter<UserContract.Model, UserContract.View> {
    @Override
    protected UserContract.Model createModel() {
        return new UserModel();
    }


    public void requestData(){

    }
}
