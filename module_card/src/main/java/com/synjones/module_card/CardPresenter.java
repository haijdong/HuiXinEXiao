package com.synjones.module_card;



import com.synjones.common_base.mvp.BasePresenter;


/**
 * author  :  donghaijun
 * data    :  2019/3/15
 * version :  1.0
 * des     :  $des$
 */
public class CardPresenter extends BasePresenter<CardContract.Model, CardContract.View> {
    @Override
    protected CardContract.Model createModel() {
        return new CardModel();
    }


    public void requestData(){

    }
}
