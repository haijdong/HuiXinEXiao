package com.synjones.huixinexiao.common_base.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.synjones.huixinexiao.common_base.app.AppConstants;
import com.synjones.huixinexiao.common_base.app.ViewManager;
import com.synjones.huixinexiao.common_base.utils.StatusBarUtil;
import com.synjones.huixinexiao.common_base.utils.SystemBarUtil;
import com.synjones.huixinexiao.common_base.utils.Utils;
import com.synjones.huixinexiao.common_base.view.loading.ProgressDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;


/**
 * author  :  donghaijun
 * data    :  2019/3/8
 * version :  1.0
 * des     :  activity 基类
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    protected ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewManager.getInstance().addActivity(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initSystemBarStyle(getSystemBarColor());
        StatusBarUtil.darkMode(this);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (useEventBus()) {
            EventBus.getDefault().register(this);//注册eventBus
        }
        initView();
        initData();
        initListener();
    }

    /**
     * 设置沉浸式标题栏
     *
     * @param systemBarColor
     */
    protected void initSystemBarStyle(int systemBarColor) {
        if (systemBarColor != 0) {
            try {
                SystemBarUtil.initSystemBarStyle(this, systemBarColor);
            } catch (Exception e) {
            }
        }
    }

    /**
     * 获取沉浸式标题栏颜色
     *
     * @return
     */
    protected int getSystemBarColor() {
        return 0;
    }

    /**
     * 是否使用eventBus
     *
     * @return
     */
    protected boolean useEventBus() {
        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewManager.getInstance().addActivity(this);
        if (useEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }

    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过ARouter跳转界面
     * @param path 路径
     */
    public void startActivity(String path) {
        ARouter.getInstance().build(path).navigation();
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
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
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

//    /**
//     * 显示带消息的进度框
//     *
//     * @param title 提示
//     */
//    protected void showLoadingDialog(String title) {
//        createLoadingDialog();
//        loadingDialog.setMessage(title);
//        if (!loadingDialog.isShowing())
//            loadingDialog.show();
//    }

    /**
     * 显示进度框
     */
    protected void showLoadingDialog() {
        if (null != mDialog && !mDialog.isShowing() && !isFinishing()) {
            mDialog.show();
        }
    }

    /**
     * 创建LoadingDialog
     */
    private void createLoadingDialog(Context context, boolean canCloseable, String msg) {
        if (isLoadingDialogShowing()) {
            mDialog.dismiss();
        }
        mDialog = new ProgressDialog(context, canCloseable, msg);
        mDialog.setOnItemClickListener(new ProgressDialog.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                finish();
            }
        });
    }

    /**
     * 隐藏进度框
     */
    protected void hideLoadingDialog() {
        if (isLoadingDialogShowing()) {
            mDialog.dismiss();
        }
    }


    /**
     * 获取弹窗是否正在显示
     *
     * @return
     */
    protected boolean isLoadingDialogShowing() {
        if (null != mDialog) {
            return mDialog.isShowing();
        }
        return false;
    }

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


    /**
     * 添加fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragment(BaseFragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();

    }


    /**
     * 替换fragment
     *
     * @param fragment
     * @param frameId
     */
    protected void replaceFragment(BaseFragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();

    }


    /**
     * 隐藏fragment
     *
     * @param fragment
     */
    protected void hideFragment(BaseFragment fragment) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .hide(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 显示fragment
     *
     * @param fragment
     */
    protected void showFragment(BaseFragment fragment) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .show(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 移除fragment
     *
     * @param fragment
     */
    protected void removeFragment(BaseFragment fragment) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss();

    }


    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
