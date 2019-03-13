package com.synjones.common_base.base;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.synjones.common_base.mvp.BasePresenter;
import com.synjones.common_base.mvp.IView;

/**
 * author  :  donghaijun
 * data    :  2019/3/8
 * version :  1.0
 * des     :  BaseMvpActivity
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements IView {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }


    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
