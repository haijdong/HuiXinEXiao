package com.synjones.huixinexiao.module_school;



import android.view.View;

import com.synjones.huixinexiao.common_base.base.BaseFragment;


/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
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
    protected SchoolPresenter createPresenter() {
        return new SchoolPresenter();
    }

    @Override
    protected void initView() {
        mView = View.inflate(getActivity(), R.layout.fragment_school, null);
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
