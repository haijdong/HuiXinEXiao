package com.synjones.module_main.main;


import com.synjones.common_base.base.BaseMvpActivity;
import com.synjones.module_main.R;
import com.synjones.module_main.data.entity.TestNews;

import java.util.List;


public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {



    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
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
}
