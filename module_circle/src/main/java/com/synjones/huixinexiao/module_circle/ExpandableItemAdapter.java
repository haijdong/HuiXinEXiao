package com.synjones.huixinexiao.module_circle;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import androidx.annotation.NonNull;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private static final String TAG = ExpandableItemAdapter.class.getSimpleName();

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
//    public static final int TYPE_PERSON = 2;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ExpandableItemAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.item_expandable_lv0);
        addItemType(TYPE_LEVEL_1, R.layout.item_expandable_lv1);
//        addItemType(TYPE_PERSON, R.layout.item_expandable_lv2);
    }


    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                switch (holder.getLayoutPosition() %
                        3) {
                    case 0:
                        holder.setImageResource(R.id.iv_head, R.drawable.head_img0);
                        break;
                    case 1:
                        holder.setImageResource(R.id.iv_head, R.drawable.head_img1);
                        break;
//                    case 2:
//                        holder.setImageResource(R.id.iv_head, R.drawable.head_img2);
//                        break;
                }
                final Level0Item lv0 = (Level0Item) item;
                holder.setText(R.id.title, lv0.title)
                        .setText(R.id.sub_title, lv0.subTitle)
                        .setImageResource(R.id.iv, lv0.isExpanded() ? R.drawable.arrow_b : R.drawable.arrow_r);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (lv0.isExpanded()) {
                            collapse(pos);
                        } else {
//                            if (pos % 3 == 0) {
//                                expandAll(pos, false);
//                            } else {
                            expand(pos);
//                            }
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                final Level1Item lv1 = (Level1Item) item;


                holder.setText(R.id.title, lv1.title)
                        .setText(R.id.sub_title, lv1.subTitle);

                TextView textView = holder.getView(R.id.delete);
                LinearLayout mitemView = holder.getView(R.id.itemView);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 获取当前父级位置
                        int cp = getParentPosition(lv1);
                        // 通过父级位置找到当前list，删除指定下级
                        ((Level0Item) getData().get(cp)).removeSubItem(lv1);
                        // 列表层删除相关位置的数据
                        getData().remove(holder.getLayoutPosition());
                        notifyDataSetChanged();
                    }
                });

                mitemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showPopWindow(new baseAppCallback() {
                            @Override
                            public void onError(int var1, String var2) {

                            }

                            @Override
                            public void onSuccess() {
                                //获取当前父级位置
                                int cp = getParentPosition(lv1);
                                // 通过父级位置找到当前list，删除指定下级
                                ((Level0Item) getData().get(cp)).removeSubItem(lv1);
                                // 列表层删除相关位置的数据
                                getData().remove(holder.getLayoutPosition());
                                notifyDataSetChanged();
                            }
                        });
                        return true;
                    }
                });


                break;
        }
    }


    private PopupWindow popupWindow;

    public void showPopWindow(@NonNull final baseAppCallback callback) {
        // 加载popupWindow的布局文件

        String infServie = LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(infServie);
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_notice, null, false);
        TextView cannal = (TextView) view.findViewById(R.id.cannal);
        TextView iv_commit = (TextView) view.findViewById(R.id.iv_commit);
        TextView mtitle = (TextView) view.findViewById(R.id.title);
        TextView mdescribe = (TextView) view.findViewById(R.id.describe);


        cannal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != popupWindow) {
                    popupWindow.dismiss();
                    callback.onSuccess();
                }
            }
        });
        iv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (null != popupWindow) {
                    popupWindow = null;
                    callback.onError(0, "");
                }
            }
        });
        popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.color.white_60));
        popupWindow.setOutsideTouchable(true);
    }

    private void closePopWindow() {
        popupWindow.dismiss();
        popupWindow = null;
    }

    public interface baseAppCallback {
        void onError(int var1, String var2);

        void onSuccess();
    }
}
