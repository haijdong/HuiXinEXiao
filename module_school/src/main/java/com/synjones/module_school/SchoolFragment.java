package com.synjones.module_school;



import com.synjones.common_base.base.BaseFragment;


/**
 * author  :  donghaijun
 * data    :  2019/3/15
 * version :  1.0
 * des     :  $des$
 */
public class SchoolFragment extends BaseFragment<SchoolPresenter> implements SchoolContract.View {



    public static SchoolFragment getInstance() {
        SchoolFragment fragment = new SchoolFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
//        fragment.mTitle = title;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_school;
    }

    @Override
    protected SchoolPresenter createPresenter() {
        return new SchoolPresenter();
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
