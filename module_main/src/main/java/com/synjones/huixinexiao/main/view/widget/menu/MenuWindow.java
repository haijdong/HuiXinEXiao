package com.synjones.huixinexiao.main.view.widget.menu;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.synjones.huixinexiao.module_main.R;

/**
 * @author  :  donghaijun
 * @data    :  2019/3/15
 * @version :  1.0
 * @des     :  $des$
 */
public class MenuWindow extends PopupWindow implements OnClickListener {

    private String TAG = MenuWindow.class.getSimpleName();
    Activity mContext;
    private int mWidth;
    private int mHeight;
    private int statusBarHeight;
    private Bitmap mBitmap = null;
    private Bitmap overlay = null;
    private RelativeLayout layout;
    private Handler mHandler = new Handler();

    private TypeSelectCallBack mTypeSelectCallBack;


    public MenuWindow(Activity context, TypeSelectCallBack callBack) {
        mContext = context;
        mTypeSelectCallBack = callBack;
    }

    public void init() {
        Rect frame = new Rect();
        mContext.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusBarHeight = frame.top;
        DisplayMetrics metrics = new DisplayMetrics();
        mContext.getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;

        setWidth(mWidth);
        setHeight(mHeight);
    }

    private Bitmap blur() {
        if (null != overlay) {
            return overlay;
        }
        long startMs = System.currentTimeMillis();

        View view = mContext.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        mBitmap = view.getDrawingCache();

        float scaleFactor = 8;
        float radius = 10;
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        overlay = Bitmap.createBitmap((int) (width / scaleFactor), (int) (height / scaleFactor), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(overlay);
        canvas.scale(1 / scaleFactor, 1 / scaleFactor);
        Paint paint = new Paint();
        paint.setFlags(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(mBitmap, 0, 0, paint);

        overlay = FastBlur.doBlur(overlay, (int) radius, true);
        Log.i(TAG, "blur time is:" + (System.currentTimeMillis() - startMs));
        return overlay;
    }

    private Animation showAnimation1(final View view, int fromY, int toY) {
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation go = new TranslateAnimation(0, 0, fromY, toY);
        go.setDuration(300);
        TranslateAnimation go1 = new TranslateAnimation(0, 0, -10, 2);
        go1.setDuration(100);
        go1.setStartOffset(250);
        set.addAnimation(go1);
        set.addAnimation(go);

        set.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationStart(Animation animation) {

            }

        });
        return set;
    }


    public void showMoreWindow(View anchor, int bottomMargin) {
        layout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.center_music_more_window, null);
        setContentView(layout);

        ImageView close = (ImageView) layout.findViewById(R.id.center_music_window_close);
        RelativeLayout out = (RelativeLayout) layout.findViewById(R.id.out);
        android.widget.RelativeLayout.LayoutParams params = new android.widget.RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.bottomMargin = bottomMargin;
        params.addRule(RelativeLayout.BELOW, R.id.card_transfer);
        params.addRule(RelativeLayout.RIGHT_OF, R.id.card_bill);
        params.topMargin = 200;
        params.leftMargin = 18;
        close.setLayoutParams(params);

        close.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isShowing()) {
                    if (null != mTypeSelectCallBack) {
                        mTypeSelectCallBack.onTypeSelected(6);
                    }
                    closeAnimation(layout);
                }
            }

        });

        out.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeAnimation(layout);
            }
        });

        showAnimation(layout);
//        setBackgroundDrawable(new BitmapDrawable(mContext.getResources(), blur()));

        setOutsideTouchable(true);
        setFocusable(true);
        showAtLocation(anchor, Gravity.BOTTOM, 0, statusBarHeight);
    }

    private void showAnimation(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            if (child.getId() == R.id.center_music_window_close) {
                continue;
            }
            child.setOnClickListener(this);
            child.setVisibility(View.INVISIBLE);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                    fadeAnim.setDuration(300);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(150);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, i * 50);
        }

    }

    private void closeAnimation(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            final View child = layout.getChildAt(i);
            if (child.getId() == R.id.center_music_window_close) {
                continue;
            }
            child.setOnClickListener(this);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 0, 600);
                    fadeAnim.setDuration(200);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(100);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                    fadeAnim.addListener(new AnimatorListener() {

                        @Override
                        public void onAnimationStart(Animator animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {
                            // TODO Auto-generated method stub

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            child.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            // TODO Auto-generated method stub

                        }
                    });
                }
            }, (layout.getChildCount() - i - 1) * 30);

            if (child.getId() == R.id.card_scan) {
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        dismiss();
                    }
                }, (layout.getChildCount() - i) * 30 + 80);
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.card_scan) {
            if (null != mTypeSelectCallBack) {
                mTypeSelectCallBack.onTypeSelected(1);
            }

            closeAnimation(layout);
        } else if (v.getId() == R.id.card_authcode) {
            if (null != mTypeSelectCallBack) {
                mTypeSelectCallBack.onTypeSelected(2);
            }
            closeAnimation(layout);
        } else if (v.getId() == R.id.card_paycode) {
            if (null != mTypeSelectCallBack) {
                mTypeSelectCallBack.onTypeSelected(3);
            }

            closeAnimation(layout);

        } else if (v.getId() == R.id.card_bill) {
            if (null != mTypeSelectCallBack) {
                mTypeSelectCallBack.onTypeSelected(4);
            }

            closeAnimation(layout);

        } else if (v.getId() == R.id.card_transfer) {
            if (null != mTypeSelectCallBack) {
                mTypeSelectCallBack.onTypeSelected(5);
            }

            closeAnimation(layout);

        }

    }

    public void destroy() {
        if (null != overlay) {
            overlay.recycle();
            overlay = null;
            System.gc();
        }
        if (null != mBitmap) {
            mBitmap.recycle();
            mBitmap = null;
            System.gc();
        }
    }


    /**
     * 选中回调
     */
    public interface TypeSelectCallBack {
        void onTypeSelected(int action);
    }
}
