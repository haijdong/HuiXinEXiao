package com.synjones.huixinexiao.module_card;



import android.view.View;

import com.synjones.huixinexiao.common_base.base.BaseFragment;


/**
 * author  :  donghaijun
 * data    :  2019/3/15
 * version :  1.0
 * des     :  $des$
 */
public class CardFragment extends BaseFragment<CardPresenter> implements CardContract.View {



    public static CardFragment getInstance() {
        CardFragment fragment = new CardFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
//        fragment.mTitle = title;
        return fragment;
    }


    @Override
    protected CardPresenter createPresenter() {
        return new CardPresenter();
    }

    @Override
    protected void initView() {
        mView = View.inflate(getActivity(), R.layout.fragment_card, null);
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
