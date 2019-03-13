package com.synjones.common_base.mvp;

/**
 * author  :  donghaijun
 * data    :  2019/3/8
 * version :  1.0
 * des     :  $des$
 */
public interface IView {


    //显示loading
    void showLoading();

    //隐藏loading
    void hideLoading();

    //显示吐司
    void showError(String msg);
}
