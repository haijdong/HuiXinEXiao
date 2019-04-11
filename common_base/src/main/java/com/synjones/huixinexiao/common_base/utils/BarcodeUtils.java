package com.synjones.huixinexiao.common_base.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.synjones.huixinexiao.common_base.R;
import com.synjones.huixinexiao.common_base.app.BaseApplication;


import java.util.Hashtable;

import androidx.core.content.ContextCompat;


public class BarcodeUtils {
    private final static String PREFIX_USER = "user:";
    private final static String PREFIX_GROUP = "group:";
    private final static String PREFIX_PAY = "merCode";
    private final static String PREFIX_AAPAY = "AApay";

    public enum ScanResultType {
        None,
        User,
        Group,
        PAY,
        AA
    }

    public static class ScanResult {
        public ScanResultType type;
        public String data = "";

        public ScanResult() {
        }
    }

    private BarcodeUtils() {
    }

    public static ScanResult getResult(String resultString) {
        ScanResult result = new ScanResult();
        if (TextUtils.isEmpty(resultString)) {
            result.type = ScanResultType.None;
            return result;
        }

        if (resultString.startsWith(PREFIX_USER)) {
            result.type = ScanResultType.User;
            if (resultString.length() > PREFIX_USER.length()) {
                result.data = resultString.substring(PREFIX_USER.length());
            }
        } else if (resultString.startsWith(PREFIX_GROUP)) {
            result.type = ScanResultType.Group;
            if (resultString.length() > PREFIX_GROUP.length()) {
                result.data = resultString.substring(PREFIX_GROUP.length());
            }
        } else if (resultString.startsWith(PREFIX_PAY)) {

            result.type = ScanResultType.PAY;
        } else if (resultString.startsWith(PREFIX_AAPAY)) {

            result.type = ScanResultType.AA;
        }

        return result;
    }

    public static Bitmap generateUserQRCodeById(String userid) {
        try {
            return generateQRCode(PREFIX_USER + userid, 300, 300);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static Bitmap generateUserQRCode(UserMe user) {
//        try {
//            return generateQRCode(PREFIX_USER + user.getPhoneNumber(), 300, 300);
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static Bitmap generateCodeByStr(String str) {
        try {
            return generateQRCode(str, 115, 115);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据服务器返回的数值生成二维码
     *
     * @param str    数值内容
     * @param margin 白边
     * @return 图片或空
     */
    public static Bitmap generateCodeByStr(String str, int margin) {
        try {
            return generateQRCode(str, 300, 300, margin);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap generateUserQRCode(String userCode) {
        try {
            return generateQRCode(PREFIX_USER + userCode, 300, 300);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap generatePayQRCode(String str) {
        try {
            return generateQRCode(str, 300, 300);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成一维码
     * （*重要*）生成一维条码,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
     *
     * @param content 生成的内容
     * @return bitMap一维码图片
     */
    public static Bitmap CreateOneDCode(String content) {
        // 生成一维条码,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(content,
                    BarcodeFormat.CODE_128, 500, 240);
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (matrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height,
                    Bitmap.Config.ARGB_8888);
            // 通过像素数组生成bitmap,具体参考api
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap generatePaymentQRCode(String token) {
        try {
            return generateQRCode(token, 300, 300);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Bitmap generateQRCode(String str, int width, int height) throws WriterException {
        //生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败  
        BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, width, height);
        width = matrix.getWidth();
        height = matrix.getHeight();
        //二维矩阵转为一维像素数组,也就是一直横着排了  
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;
                }

            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //通过像素数组生成bitmap,具体参考api  
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }


    public static Bitmap generateUserQRCode(String str, int margin) {
        try {
            return generateQRCode(PREFIX_USER + str, 300, 300, margin);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成二维码
     *
     * @param str    二维码内容
     * @param width  宽
     * @param height 高
     * @param margin 设置白边 margin取值0~4
     * @throws WriterException
     */
    public static Bitmap generateQRCode(String str, int width, int height, int margin) throws WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        // 指定纠错等级
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, margin);   //设置白边   margin取值0~4
        //MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);
        BitMatrix matrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, 300, 300, hints);
        width = matrix.getWidth();
        height = matrix.getHeight();
        //二维矩阵转为一维像素数组,也就是一直横着排了
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (matrix.get(x, y)) {
                    pixels[y * width + x] = 0xff000000;// 颜色黑色
                }
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //通过像素数组生成bitmap,具体参考api
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        Drawable drawable = ContextCompat.getDrawable(BaseApplication.getIns(), R.drawable.xtoast_error);
        BitmapDrawable bd = (BitmapDrawable)drawable ;
        Bitmap logoBm= bd.getBitmap();
        if (logoBm != null) {
            bitmap = addLogo(bitmap, logoBm);
        }
        return bitmap;
    }


    /**
     * 在二维码中间添加Logo图案
     */
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }
        if (logo == null) {
            return src;
        }
        //获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);
            canvas.save();
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }
}
