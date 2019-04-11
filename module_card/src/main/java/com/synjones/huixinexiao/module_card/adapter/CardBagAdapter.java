package com.synjones.huixinexiao.module_card.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.synjones.huixinexiao.module_card.data.entity.CardInfo;
import java.util.List;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;


/**
 * Created by donghaijun on 2017/10/30.
 */

public class CardBagAdapter extends BaseQuickAdapter<CardInfo,BaseViewHolder> {


    public CardBagAdapter(@LayoutRes int layoutResId, @Nullable List<CardInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardInfo item) {


    }
}
