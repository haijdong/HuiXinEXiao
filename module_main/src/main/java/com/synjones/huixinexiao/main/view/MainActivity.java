package com.synjones.huixinexiao.main.view;


import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.synjones.huixinexiao.common_base.app.AppConstants;
import com.synjones.huixinexiao.common_base.app.ClassUtils;
import com.synjones.huixinexiao.common_base.app.IViewDelegate;
import com.synjones.huixinexiao.common_base.app.ViewManager;
import com.synjones.huixinexiao.common_base.base.BaseFragment;
import com.synjones.huixinexiao.common_base.base.BaseMvpActivity;
import com.synjones.huixinexiao.common_base.view.NoScrollViewPager;
import com.synjones.huixinexiao.module_main.R;
import com.synjones.huixinexiao.main.data.entity.TestNews;
import com.synjones.huixinexiao.main.adapter.FragmentAdapter;
import com.synjones.huixinexiao.main.persenter.impl.MainPresenter;
import com.synjones.huixinexiao.main.persenter.MainContract;
import com.synjones.huixinexiao.main.view.widget.menu.MenuWindow;

import java.util.List;

import io.reactivex.annotations.NonNull;
/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
@Route(path = AppConstants.ACTIVITY_URL_MAIN)
public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {

    private MenuWindow mMoreWindow;

    private NoScrollViewPager mPager;
    private List<BaseFragment> mFragments;
    private FragmentAdapter mAdapter;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.navigation_home) {
                mPager.setCurrentItem(3);
                return true;
            } else if (i == R.id.navigation_school) {
                mPager.setCurrentItem(1);
                return true;
            } else if (i == R.id.navigation_card) {
                showMenu(navigation);
                return true;
            } else if (i == R.id.navigation_circle) {
                mPager.setCurrentItem(2);
                return true;
            } else if (i == R.id.navigation_user) {
                mPager.setCurrentItem(0);
                return true;
            }
            return false;
        }

    };


    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initViewPager();
        navigation.setSelectedItemId(R.id.navigation_home);

    }

    private void initViewPager() {
        //这几个Fragment是主动添加到ViewManager中的
        mFragments = ViewManager.getInstance().getAllFragment();
        mPager = (NoScrollViewPager) findViewById(R.id.container_pager);
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mPager.setPagerEnabled(false);
        mPager.setAdapter(mAdapter);
    }

    /**
     * 在News模块中寻找实现的Fragment
     *
     * @return Fragment
     */
    private BaseFragment getNewsFragment() {
        BaseFragment newsFragment = null;
        List<IViewDelegate> viewDelegates = ClassUtils.getObjectsWithInterface(this, IViewDelegate.class, "com.synjones.huixinexiao.module_school");
        if (viewDelegates != null && !viewDelegates.isEmpty()) {
            newsFragment = viewDelegates.get(0).getFragment("");
        }
        return newsFragment;
    }


    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void showData(List<TestNews> testNews) {

    }

    @Override
    public void showError(String msg) {

    }

    private void showMenu(View view) {
        if (null == mMoreWindow) {
            mMoreWindow = new MenuWindow(this, new MenuWindow.TypeSelectCallBack() {
                @Override
                public void onTypeSelected(int action) {
                    if (action == 1) {
                        startActivity(AppConstants.ACTIVITY_URL_SCAN);
                    } else if (action == 2) {
                        //认证码
                        startActivity(AppConstants.ACTIVITY_URL_AUTHCODE);
                    } else if (action == 3) {
                        //付款码
                        startActivity(AppConstants.ACTIVITY_URL_PAYCODE);
                    } else if (action == 4) {
                        //账单

                    } else if (action == 5) {
                        //转账

                    }
                }
            });
            mMoreWindow.init();
        }
        mMoreWindow.showMoreWindow(view, 100);
    }

    // 点击两次退出程序
    private static final long EXIT_TIME = 2000;
    private long mLastRunBackgroundTime;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            long curTime = System.currentTimeMillis();
            if (mLastRunBackgroundTime == 0) {
                mLastRunBackgroundTime = curTime;
                Toast.makeText(this, R.string.exit_system, Toast.LENGTH_SHORT)
                        .show();
            } else {
                if (curTime - mLastRunBackgroundTime > EXIT_TIME) {
                    Toast.makeText(this, R.string.exit_system,
                            Toast.LENGTH_SHORT).show();
                    mLastRunBackgroundTime = curTime;
                } else {
                    finish();
                }
            }
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

}
