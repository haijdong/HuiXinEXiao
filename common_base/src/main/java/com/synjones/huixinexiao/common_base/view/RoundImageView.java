package com.synjones.huixinexiao.common_base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by donghaijun on 2017/9/28.
 */
public class RoundImageView extends ImageView {

    float width,height;

    private final int corners = 50;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (width > corners && height > corners) {
            Path path = new Path();
            path.moveTo(corners, 0);
            path.lineTo(width - corners, 0);


            path.quadTo(width, 0, width, corners);
            path.lineTo(width, height - corners);
            path.quadTo(width, height, width, height);
//            path.quadTo(width, height, width - corners, height);


            path.lineTo(corners, height);
            path.quadTo(0, height, 0, height);
//            path.quadTo(0, height, 0, height - corners);



            path.lineTo(0, corners);
            path.quadTo(0, 0, corners, 0);


            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }
}

