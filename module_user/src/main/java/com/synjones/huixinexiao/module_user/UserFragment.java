package com.synjones.huixinexiao.module_user;



import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.synjones.huixinexiao.common_base.base.BaseFragment;
import com.synjones.huixinexiao.common_base.glide.GlideUtils;
import com.synjones.huixinexiao.common_base.utils.StatusBarUtil;
import com.synjones.huixinexiao.common_base.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.viewpager.widget.ViewPager;


/**
 * author  :  donghaijun
 * data    :  2019/3/15
 * version :  1.0
 * des     :  $des$
 */
public class UserFragment extends BaseFragment<UserPresenter> implements UserContract.View, View.OnClickListener {


    private ViewPager mViewPager;
    private int pagerWidth;
    private CardPagerAdapter mPagerAdapter;
    private List<View> imageViews = new ArrayList<>();


    public static UserFragment getInstance() {
        UserFragment fragment = new UserFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
//        fragment.mTitle = title;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected UserPresenter createPresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 4.0f / 5.0f);
        ViewGroup.LayoutParams lp = mViewPager.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            lp.width = pagerWidth;
        }
        mViewPager.setLayoutParams(lp);
        mViewPager.setPageMargin(50);
        //        mViewPager.setPageTransformer(true, new GallyPageTransformer());

//        mViewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
////                        handler.sendEmptyMessage(1);
//                        startActivity(XYSendShowDetailActivity.class);
//                        break;
//                    case MotionEvent.ACTION_UP:
////                        handler.sendEmptyMessageDelayed(0, 2000);
//                        break;
//                }
//                return false;
//            }
//        });



        for (int i = 0; i < 3; i++) {
            View view = View.inflate(getActivity(),R.layout.item_cardview,null);
            RoundImageView roundImageView = (RoundImageView) view.findViewById(R.id.imageview);
            GlideUtils.loadImage(getActivity(),roundImageView,"http://image.baidu.com/search/detail?ct=503316480&z=0&tn=baiduimagedetail&ipn=d&cl=2&cm=1&sc=0&lm=-1&ie=gb18030&pn=1&rn=1&di=170575271240&ln=30&word=%CD%BC%C6%AC&os=203864387,74456455&cs=873265023,1618187578&objurl=http%3A%2F%2Fpic22.nipic.com%2F20120714%2F9622064_105642209176_2.jpg&bdtype=0&simid=3368376108,149779417&pi=0&adpicid=0&timingneed=0&spn=0&is=0,0&fr=ala&ala=1&alatpl=others&pos=1",R.drawable.ic_launcher_foreground);
            imageViews.add(view);
        }

        mPagerAdapter = new CardPagerAdapter(imageViews);
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    protected void initListener() {
        findViewById(R.id.exit).setOnClickListener(this);
        findViewById(R.id.setting).setOnClickListener(this);
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


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.exit) {

        } else if (v.getId() == R.id.setting) {


        }
    }
}
