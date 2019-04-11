package com.synjones.huixinexiao.module_main.main.view;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;


import com.alibaba.android.arouter.launcher.ARouter;
import com.synjones.huixinexiao.common_base.app.AppConstants;
import com.synjones.huixinexiao.common_base.base.BaseActivity;
import com.synjones.huixinexiao.common_base.db.SchoolInfoPreferences;
import com.synjones.huixinexiao.common_base.db.UserInfoPreferences;
import com.synjones.huixinexiao.common_base.utils.StringUtils;
import com.synjones.huixinexiao.module_main.R;

import okio.ByteString;

public class WelcomeActivity extends BaseActivity {

    private TextView mJumpActivity;
    private int second = 3;
    private ImageView mZheng;
    private ImageView mFan;

    @SuppressLint("HandlerLeak")
    Handler mHander = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            second--;
            mJumpActivity.setText("跳过 " + second);
            mHander.sendEmptyMessageDelayed(0, 1000);
            if (second == 0) {
                jumpMain();
            }

        }
    };

    /**
     * SNO
     * <p>
     * 学校代码
     */
    private String mSno, mSchoolId = "";



    @Override
    protected void onResume() {
        super.onResume();
        mHander.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mHander.removeMessages(0);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_welcome);
        mJumpActivity = (TextView) findViewById(R.id.jump);


        mZheng = findViewById(R.id.zheng);
        mFan = findViewById(R.id.fan);
        startAnimation();
    }

    @Override
    protected void initListener() {
        findViewById(R.id.jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpMain();
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void startAnimation() {
        //顺时针旋转180f
        ObjectAnimator rotateZheng = ObjectAnimator.ofFloat(mZheng, "rotation", 0f, 180f).setDuration(2500);
        rotateZheng.setInterpolator(new LinearInterpolator());

        //逆时针旋转180f
        ObjectAnimator rotateFan = ObjectAnimator.ofFloat(mFan, "rotation", 180f, 0f).setDuration(2500);
        rotateFan.setInterpolator(new LinearInterpolator());
        AnimatorSet set = new AnimatorSet();
        set.playTogether(rotateZheng, rotateFan);

        set.addListener(new AnimatorListenerAdapter() {


            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
//                jumpMain();
            }
        });
        set.start();

    }

    public void jumpMain() {
       startActivity(AppConstants.ACTIVITY_URL_MAIN);

//        if (!StringUtils.isEmpty(SchoolInfoPreferences.getInstance().getSchoolInfo().getSchoolName())) {
//
//            byte[] pwd = UserInfoPreferences.getInstance().getGesturePassword();
//            boolean fingerprintpwd = UserInfoPreferences.getInstance().getFingerprintpwd();
//            if (fingerprintpwd || pwd != null) {
//                if ( !StringUtils.isEmpty(ByteString.of(pwd).hex()) || fingerprintpwd) {
//                    startActivity(GestureLoginActivity.class);
//                } else {
//                    startActivity(MainActivity.class);
//                }
//            } else {
//                startActivity(MainActivity.class);
//            }
//            finish();
//        } else {
//            startActivity(LoginSchoolActivity.class);
//        }
        finish();
    }
}
