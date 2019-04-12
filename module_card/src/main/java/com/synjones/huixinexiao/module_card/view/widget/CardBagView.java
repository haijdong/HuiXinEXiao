package com.synjones.huixinexiao.module_card.view.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.synjones.huixinexiao.module_card.R;
import com.synjones.huixinexiao.module_card.adapter.CardBagAdapter;
import com.synjones.huixinexiao.module_card.data.entity.CardInfo;


import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class CardBagView extends PopupWindow {

    private TypeSelectCallBack mTypeSelectCallBack;
    private CardBagAdapter adapter;
    private View mMenuView;
    private RecyclerView recyclerView;
    private ImageView iv_close;
    private List<CardInfo> list = new ArrayList<>();
    private ImageView iv_empty;


    public CardBagView(final Context context, final List<CardInfo> list, TypeSelectCallBack callBack) {
        super(context);
        this.list = list;
        mTypeSelectCallBack = callBack;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.view_cardbag, null);
        recyclerView = (RecyclerView) mMenuView.findViewById(R.id.recycleview);
        iv_empty = (ImageView) mMenuView.findViewById(R.id.iv_empty);
        iv_close = (ImageView) mMenuView.findViewById(R.id.iv_close);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CardBagAdapter(R.layout.view_item_card, list);
        recyclerView.setAdapter(adapter);
        iv_close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (null != mTypeSelectCallBack) {
                    dismiss();
                    mTypeSelectCallBack.onTypeSelected(list.get(position));
                }
            }
        });

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.FILL_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimBottom);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }

    /**
     * 选中回调
     */
    public interface TypeSelectCallBack {
        void onTypeSelected(CardInfo info);
    }

}
