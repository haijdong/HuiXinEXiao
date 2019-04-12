package com.synjones.huixinexiao.module_user.view.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.synjones.huixinexiao.common_base.base.BaseMvpActivity;
import com.synjones.huixinexiao.common_base.db.UserInfoPreferences;
import com.synjones.huixinexiao.common_base.utils.StringUtils;
import com.synjones.huixinexiao.common_base.utils.XToast;
import com.synjones.huixinexiao.common_base.view.rootview.TitleBar;
import com.synjones.huixinexiao.module_user.R;
import com.synjones.huixinexiao.module_user.persenter.SettingContract;
import com.synjones.huixinexiao.module_user.persenter.impl.SettingPresenter;
import com.synjones.huixinexiao.module_user.view.widget.FingerprintDialogFragment;

import java.security.KeyStore;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import okio.ByteString;

/**
 * 设置界面
 * <p>
 * author: donghaijun
 * -----------------
 * data:   2019/2/22
 */
public class SettingActivity extends BaseMvpActivity<SettingPresenter> implements SettingContract.View, View.OnClickListener, FingerprintDialogFragment.OnDialogResultListener, CompoundButton.OnCheckedChangeListener {
    private static final String DEFAULT_KEY_NAME = "default_key";
    private KeyStore keyStore;


    private Switch mGestureSw;
    private Switch mFingerSw;

    private TitleBar mTitle;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_setting);
        mGestureSw = (Switch) findViewById(R.id.gesture_switch);
        mFingerSw = (Switch) findViewById(R.id.fingerpr_switch);
    }

    @Override
    protected void initListener() {
        findViewById(R.id.reset_gesture).setOnClickListener(this);
        findViewById(R.id.forget_gesture).setOnClickListener(this);
        findViewById(R.id.create_gesture).setOnClickListener(this);
        findViewById(R.id.fingerpr).setOnClickListener(this);
        ((TitleBar) findViewById(R.id.titlebar)).setBackFinish(this);
        mGestureSw.setOnCheckedChangeListener(this);
        mFingerSw.setOnCheckedChangeListener(this);


    }


    @Override
    protected void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //是否开启了手势或者指纹密码
        if (UserInfoPreferences.getInstance().getFingerprintpwd()) {
            mFingerSw.setChecked(true);
        } else {
            mFingerSw.setChecked(false);
        }
        byte[] pwd = UserInfoPreferences.getInstance().getGesturePassword();
        if (pwd != null) {
            if (StringUtils.isEmpty(ByteString.of(pwd).hex())) {
                mGestureSw.setChecked(false);
            } else {
                mGestureSw.setChecked(true);
            }

        } else {
            mGestureSw.setChecked(false);
        }


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.create_gesture) {
            //                byte[] pwd = UserInfoPreferences.getInstance().getGesturePassword();
//                if (pwd != null) {
//                    if (StringUtils.isEmpty(ByteString.of(pwd).hex())) {
//                        startActivity(CreateGestureActivity.class);
//                    } else {
////                        XToast.custom("关闭手势");
//                        UserInfoPreferences.getInstance().removeGesturePassword();
//                    }
//                } else {
//                    startActivity(CreateGestureActivity.class);
//                }
        } else if (v.getId() == R.id.forget_gesture) {

        } else if (v.getId() == R.id.reset_gesture) {
//            startActivity(GestureLoginActivity.class);
        } else if (v.getId() == R.id.fingerpr) {

        }

    }

    /**
     * Android M+ 指纹
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void initFingerprintManager() {
        initKey();
        Cipher cipher = initCipher();
        // 指纹识别弹窗
        showFingerPrintDialog(cipher);
    }

    /**
     * 创建密钥
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void initKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(DEFAULT_KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7);
            keyGenerator.init(builder.build());
            keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 初始化Cipher(加密类，指纹扫描器会使用这个对象来判断认证结果的合法性)
     */
    @TargetApi(Build.VERSION_CODES.M)
    private Cipher initCipher() {
        try {
            SecretKey key = (SecretKey) keyStore.getKey(DEFAULT_KEY_NAME, null);
            Cipher cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 显示指纹识别弹窗
     */
    private void showFingerPrintDialog(Cipher cipher) {
        FingerprintDialogFragment fragment = new FingerprintDialogFragment();
        fragment.setCipher(cipher);
        fragment.setOnClickListener(this);
        fragment.show(getSupportFragmentManager(), "fingerprint");
    }

    @Override
    public void success() {
        XToast.custom("指纹验证成功");
        UserInfoPreferences.getInstance().setFingerprintpwd(true);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!buttonView.isPressed()) {
            return;
        }
        if (buttonView.getId() == R.id.gesture_switch) {
            if (isChecked) {
//                startActivity(CreateGestureActivity.class);
            } else {
                UserInfoPreferences.getInstance().removeGesturePassword();
            }
        } else if (buttonView.getId() == R.id.fingerpr_switch) {
            if (isChecked) {
                initFingerprintManager();
            } else {
                UserInfoPreferences.getInstance().setFingerprintpwd(false);
            }
        }

    }


    @Override
    protected SettingPresenter createPresenter() {
        return null;
    }

    @Override
    public void showError(String msg) {

    }
}
