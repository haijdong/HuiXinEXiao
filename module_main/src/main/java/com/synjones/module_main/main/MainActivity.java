package com.synjones.module_main.main;


import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.synjones.common_base.app.ClassUtils;
import com.synjones.common_base.app.IViewDelegate;
import com.synjones.common_base.app.ViewManager;
import com.synjones.common_base.base.BaseFragment;
import com.synjones.common_base.base.BaseMvpActivity;
import com.synjones.common_base.view.NoScrollViewPager;
import com.synjones.module_main.R;
import com.synjones.module_main.data.entity.TestNews;
import com.synjones.module_main.view.menu.MenuWindow;

import java.util.List;

import io.reactivex.annotations.NonNull;


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
                mPager.setCurrentItem(0);
                return true;
            } else if (i == R.id.navigation_school) {
                mPager.setCurrentItem(1);
                return true;
            } else if (i == R.id.navigation_card) {
//                mPager.setCurrentItem(2);
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

    }

    private void initViewPager() {
        mFragments = ViewManager.getInstance().getAllFragment();//这几个Fragment是主动添加到ViewManager中的
//        BaseFragment newsFragment = getNewsFragment();//主动寻找
        //mFragments.add(newsFragment);
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
        List<IViewDelegate> viewDelegates = ClassUtils.getObjectsWithInterface(this, IViewDelegate.class, "com.synjones.module_school");
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
                    switch (action) {
                        case 1:  //扫一扫
//                            startActivity(CaptureActivity.class);

                            break;

                        case 2:  //认证码
//                            startActivity(AuthCodeActivity.class);

                            break;

                        case 3:  //付款码
//                            startActivity(PayCodeActivity.class);

                            break;

                        case 4:  //账单

                            break;

                        case 5:  //转账
//                            startActivity(AnimActivity.class);
                            break;
                    }
                }
            });
            mMoreWindow.init();
        }
        mMoreWindow.showMoreWindow(view, 100);
    }
}
