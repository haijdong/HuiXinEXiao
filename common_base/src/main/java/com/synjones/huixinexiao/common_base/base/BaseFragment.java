package com.synjones.huixinexiao.common_base.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.RefWatcher;
import com.synjones.huixinexiao.common_base.app.BaseApplication;
import com.synjones.huixinexiao.common_base.mvp.BasePresenter;
import com.synjones.huixinexiao.common_base.mvp.IView;
import com.synjones.huixinexiao.common_base.utils.Utils;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;


/**
 * author  :  donghaijun
 * data    :  2019/3/8
 * version :  1.0
 * des     :  fragment 基类
 */
public abstract class BaseFragment<T extends BasePresenter> extends RxFragment implements IView {
    protected BaseActivity mActivity;
    /**
     * 将代理类通用行为抽出来
     */
    protected T mPresenter;

    /**
     * 缓存Fragment view
     */
    protected View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null != mView) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (null != parent) {
                mView.destroyDrawingCache();
                parent.removeView(mView);
                mView = null;
            }
        } else {
            initView();
            initListener();
            initData();
        }
        return mView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter =createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

    }


    /**
     * 查找控件
     * @param viewId
     */
    public View findViewById(int viewId) {
        if (null != mView) {
            return mView.findViewById(viewId);
        }
        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) this.getActivity();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (useEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }
        initLeakCanary();
    }


    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    @Override
    public void showLoading() {
        mActivity.showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        mActivity.hideLoadingDialog();
    }


    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getBaseActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getBaseActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 通过ARouter跳转界面
     * @param path 路径
     */
    public void startActivity(String path) {
        ARouter.getInstance().build(path).navigation();
    }


    /**
     * 用来检测所有Fragment的内存泄漏
     */
    private void initLeakCanary() {
        RefWatcher refWatcher = BaseApplication.getRefWatcher(getBaseActivity());
        refWatcher.watch(this);
    }


    protected abstract T createPresenter();
    /**
     * 初始化View的代码写在这个方法中
     */
    protected abstract void initView();

    /**
     * 初始化监听器的代码写在这个方法中
     */
    protected abstract void initListener();

    /**
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    protected abstract void initData();

    protected abstract boolean useEventBus();
    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }




}
