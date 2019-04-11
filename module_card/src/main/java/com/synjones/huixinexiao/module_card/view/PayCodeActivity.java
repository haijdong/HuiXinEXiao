package com.synjones.huixinexiao.module_card.view;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.synjones.huixinexiao.common_base.app.AppConstants;
import com.synjones.huixinexiao.common_base.base.BaseActivity;
import com.synjones.huixinexiao.common_base.utils.BarcodeUtils;
import com.synjones.huixinexiao.common_base.utils.StringUtils;
import com.synjones.huixinexiao.common_base.view.rootview.TitleBar;
import com.synjones.huixinexiao.module_card.R;
import com.synjones.huixinexiao.module_card.data.entity.CardInfo;
import com.synjones.huixinexiao.module_card.view.widget.CardBagView;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;


/**
 * author: donghaijun
 * -----------------
 * data:   2018/12/17
 */
@Route(path = AppConstants.ACTIVITY_URL_PAYCODE)
public class PayCodeActivity extends BaseActivity implements View.OnClickListener, OnRefreshListener {
    private int REFRESHTIME = 1000 * 10;
    private ImageView mBarCode;
    private ImageView mQrCode;
    private TextView mBarNum;

    private ImageView mIcom;
    private TextView mBankCard;
    private TextView mBankDes;
    private RelativeLayout mCards;
    private SmartRefreshLayout mRefresh;

    private String bar_num;


    private List<CardInfo> list = new ArrayList<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_paycode);
        mBarCode = (ImageView) findViewById(R.id.iv_barcode);
        mQrCode = (ImageView) findViewById(R.id.iv_qrcode);
        mBarNum = (TextView) findViewById(R.id.tv_barcode);
        mIcom = (ImageView) findViewById(R.id.icon_pay);
        mBankCard = (TextView) findViewById(R.id.tv_bankcard);
        mBankDes = (TextView) findViewById(R.id.tv_bankdesc);
        mCards = (RelativeLayout) findViewById(R.id.cards);
        mRefresh = (SmartRefreshLayout) findViewById(R.id.refreshView);
        for (int i = 0; i < 3; i++) {
            list.add(new CardInfo());
        }

    }

    @Override
    protected void initListener() {
        ((TitleBar) findViewById(R.id.titlebar)).setBackFinish(this);
        mCards.setOnClickListener(this);
        mRefresh.setOnRefreshListener(this);
    }

    @Override
    protected void initData() {
//        bar_num = "42807769837920102047";
        long currentTimeMillis = System.currentTimeMillis();
        bar_num = currentTimeMillis + String.valueOf(currentTimeMillis).substring(5, 12);
        handler.sendEmptyMessage(0);
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case 0:
                    handler.sendEmptyMessageDelayed(0, REFRESHTIME);
//                    bar_num = "42807769837920102047";
                    long currentTimeMillis = System.currentTimeMillis();
                    bar_num = currentTimeMillis + String.valueOf(currentTimeMillis).substring(5, 12);
                    updateBarCode(bar_num);
                    break;

                default:
                    break;
            }
        }
    };

    public void updateBarCode(String bar_num) {
        mQrCode.setImageBitmap(BarcodeUtils.generateCodeByStr(bar_num, 0));
        mBarNum.setText(StringUtils.chaijie(bar_num));
        mBarCode.setImageBitmap(BarcodeUtils.CreateOneDCode(bar_num));

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cards) {
            CardBagView popShopView = new CardBagView(this, list, new CardBagView.TypeSelectCallBack() {
                @Override
                public void onTypeSelected(CardInfo info) {

                }
            });
            popShopView.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        }
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
}
