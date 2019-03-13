package com.synjones.common_base.mvp;

/**
 * author  :  donghaijun
 * data    :  2019/3/8
 * version :  1.0
 * des     :  $des$
 */
public interface IPresenter<V extends IView> {

    /**
     * 绑定 View
     */
    void attachView(V mView);

    /**
     * 解除 View
     */
    void detachView();
}
