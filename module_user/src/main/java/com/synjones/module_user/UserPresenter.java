package com.synjones.module_user;



import com.synjones.common_base.mvp.BasePresenter;



/**
 * author  :  donghaijun
 * data    :  2019/3/15
 * version :  1.0
 * des     :  $des$
 */
public class UserPresenter extends BasePresenter<UserContract.Model, UserContract.View> {
    @Override
    protected UserContract.Model createModel() {
        return new UserModel();
    }


    public void requestData(){

    }
}
