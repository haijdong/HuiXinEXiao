package com.synjones.huixinexiao.module_circle;



import android.view.View;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.synjones.huixinexiao.common_base.base.BaseFragment;
import com.synjones.huixinexiao.common_base.utils.StatusBarUtil;
import com.synjones.huixinexiao.common_base.view.rootview.TitleBar;

import java.util.ArrayList;
import java.util.Random;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class CircleFragment extends BaseFragment<CirclePresenter> implements CircleContract.View {

    private TitleBar titlebar01;



    RecyclerView mRecyclerView;
    ExpandableItemAdapter adapter;
    ArrayList<MultiItemEntity> list;

    public static CircleFragment getInstance() {
        CircleFragment fragment = new CircleFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
//        fragment.mTitle = title;
        return fragment;
    }


    @Override
    protected CirclePresenter createPresenter() {
        return new CirclePresenter();
    }

    @Override
    protected void initView() {
        mView = View.inflate(getActivity(), R.layout.fragment_circle, null);
        //状态栏透明和间距处理
        StatusBarUtil.immersive(getActivity());
        StatusBarUtil.setPaddingSmart(getActivity(), (TitleBar)findViewById(R.id.titlebar01));
        titlebar01 = (TitleBar) findViewById(R.id.titlebar01);
        titlebar01.setLeftIconVisible(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        list = generateData();
        adapter = new ExpandableItemAdapter(list);
//        final GridLayoutManager manager = new GridLayoutManager(this, 3);
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return adapter.getItemViewType(position) == ExpandableItemAdapter.TYPE_LEVEL_1 ? 1 : manager.getSpanCount();
//            }
//        });

        mRecyclerView.setAdapter(adapter);
        // important! setLayoutManager should be called after setAdapter
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter.expandAll();
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


    private ArrayList<MultiItemEntity> generateData() {
        int lv1Count = 6;
        String[] nameList = {"朋友", "同学", "家人", "同事", "黑名单"};
        Random random = new Random();

        ArrayList<MultiItemEntity> res = new ArrayList<>();
        for (int i = 0; i < nameList.length; i++) {
            Level0Item lv0 = new Level0Item(nameList[i], nameList[i]);
            for (int j = 0; j < lv1Count; j++) {
                int count = j + 1;
                Level1Item lv1 = new Level1Item(nameList[i] + "中第" + count + "个联系人", "分组描述");
                lv0.addSubItem(lv1);
            }
            res.add(lv0);
        }
        return res;
    }



}
