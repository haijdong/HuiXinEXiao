package com.synjones.huixinexiao.module_circle;



import com.synjones.huixinexiao.common_base.base.BaseFragment;


/**
 * author  :  donghaijun
 * data    :  2019/3/15
 * version :  1.0
 * des     :  $des$
 */
public class CircleFragment extends BaseFragment<CirclePresenter> implements CircleContract.View {



    public static CircleFragment getInstance() {
        CircleFragment fragment = new CircleFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
//        fragment.mTitle = title;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_circle;
    }

    @Override
    protected CirclePresenter createPresenter() {
        return new CirclePresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void showError(String msg) {

    }





}
