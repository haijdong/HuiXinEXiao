package com.synjones.huixinexiao.common_base.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.synjones.huixinexiao.common_base.app.BaseApplication;
import com.synjones.huixinexiao.common_base.base.model.UserInfo;
import com.synjones.huixinexiao.common_base.utils.StringUtils;

import okio.ByteString;


/**
 * 保存当前登录用户基本信息
 * 
 * @author liangzx
 * @version 1.0
 */
public class UserInfoPreferences {

	/**
	 * 用户ID
	 */
	private static final String USER_ID = "userId";
	/**
	 * 手机号
	 */
	private static final String TEL = "tel";
	/**
	 * 账号
	 */
	private static final String ACCOUNT = "account";
	/**
	 * 密码
	 */
	private static final String PWD = "pwd";
	/**
	 * 用户名称
	 */
	private static final String NAME = "name";
	/**
	 * 用户头像
	 */
	private static final String ICO = "ico";
	/**
	 * 性别
	 */
	private static final String SEX = "sex";
	/**
	 * token
	 */
	private static final String TOKEN = "token";
	/**
	 * 当前省份
	 */
	public static final String PROVINCE = "province";
	public static final String STATION_MAP_URL = "station_map_url";
	/**
	 * 当前城市
	 */
	public static final String CITY = "city";
	/**
	 * 城市编码
	 */
	public static final String CITY_CODE = "city_code";
	/**
	 * 经度
	 */
	public static final String LATITUDE = "latitude";
	/**
	 * 纬度
	 */
	public static final String LONGITUDE = "longitude";
	/**
	 * 地区名称
	 */
	public static final String DISTRICEE_NAME = "district_name";
	/**
	 * 地区
	 */
	public static final String DISTRICEE_CODE = "district_code";
	/**
	 * 默认微站id
	 */
	public static final String MICRO_STATION_ID = "micro_station_id";
	/**
	 * 默认微站名称
	 */
	public static final String MICRO_STATION_NAME = "micro_station_name";
	/**
	 * 地图连接
	 */
	public static final String MAP_URL = "mapurl";
	/**
	 * 其他城市
	 */
	public static final String OTHER_CITYS = "other_citys";
	/**
	 * 其他城市编码
	 */
	public static final String OTHER_CITY_CODES = "other_city_codes";
	/**
	 * 其他微站id
	 */
	public static final String OTHER_MICRO_STATION_IDS = "other_micro_station_ids";
	/**
	 * 其他微站名称
	 */
	public static final String OTHER_MICRO_STATION_NAMES = "other_micro_station_names";
	/**
	 * 区域ID列表
	 */
	public static final String OTHER_AREA_IDS = "other_area_ids";
	/**
	 * 区域名称列表
	 */
	public static final String OTHER_AREA_NAMES = "other_area_names";
	/**
	 * 默认站点
	 */
	public static final String DEF_GRID_CODES = "def_grid_codes";
	/**
	 *
	 */
	public static final String DEF_GRID_CODENAMES = "def_grid_codenames";


	/**
	 * 手势密码
	 */
	public static final String GESTURE_PASSWORD = "gesture_password";


	/**
	 * 指纹密码
	 */
	public static final String FINGERPRINT_PWD = "fingerprint_pwd";

	/**
	 * UserInfoPreferences单例
	 */
	private static UserInfoPreferences sInstance;

	/**
	 * 文件编辑器
	 */
	private Editor mEditor;

	/**
	 * 用户信息SharedPreferences
	 */
	private SharedPreferences mUserInfoSp;

	/**
	 * 文件名
	 */
	private final String FILE_NAME = "user_data";


	/**
	 * 构造
	 * 
	 * @param context
	 *            Context上下文
	 */
	public UserInfoPreferences(Context context) {
		mUserInfoSp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		mEditor = mUserInfoSp.edit();
	}

	/**
	 * 获取UserInfoPreferences单例
	 * @return UserInfoPreferences单例
	 */
	public static UserInfoPreferences getInstance() {
		if (sInstance == null) {
			sInstance = new UserInfoPreferences(BaseApplication.getIns());
		}
		return sInstance;
	}

	/**
	 * 提交保存信息
	 * 
	 * @return 是否执行成功
	 */
	public boolean commit() {
		return mEditor.commit();
	}

	/**
	 * 清除
	 */
	public void clear() {
		mEditor.clear();
	}

	/**
	 * 设置用户信息
	 * 
	 * @param data
	 */
	public void setUserData(UserInfo data) {
		mUserInfoSp.edit().putString(USER_ID, data.userId)
				.putString(NAME, data.name)
				.putString(ICO, data.ico)
				.putString(TEL, data.tel)
				.putString(ACCOUNT, data.account)
				.putString(PWD, data.pwd)
				.putInt(SEX, data.sex)
				.putString(TOKEN, data.token)
				.putString(DISTRICEE_NAME, data.district_code)
				.putString(DISTRICEE_CODE, data.district_name)
				.commit();
	}


	/**
	 * 设置手势密码
	 * @param pwd
	 */
	public void setGesturePassword (String pwd) {
		mUserInfoSp.edit().putString(GESTURE_PASSWORD,pwd).commit();
	}

	/**
	 * 获取手势密码
	 * @return
	 */
	public byte[] getGesturePassword () {
		String pwd = mUserInfoSp.getString(GESTURE_PASSWORD, "");
		if (StringUtils.isEmpty(pwd)) {
			return new byte[0];
		} else {
			return ByteString.decodeHex(pwd).toByteArray();
		}
	}

	/**
	 * 删除手势密码
	 */
	public void removeGesturePassword () {
		mUserInfoSp.edit().remove(GESTURE_PASSWORD).commit();
	}

	/**
	 * 设置指纹密码
	 * @param flag
	 */
	public void setFingerprintpwd(boolean flag) {
		mUserInfoSp.edit().putBoolean(FINGERPRINT_PWD,flag).commit();
	}

	/**
	 * 获取指纹密码
	 * @return
	 */
	public boolean getFingerprintpwd() {
		return mUserInfoSp.getBoolean(FINGERPRINT_PWD, false);
	}


	public void setMapUrl(String url) {
		mUserInfoSp.edit().putString(MAP_URL,url)
				.commit();
	}

	/**
	 * 获取用户信息
	 */
	public UserInfo getUserInfo() {
		UserInfo info = new UserInfo();
		info.userId = mUserInfoSp.getString(USER_ID, "");
		info.name = mUserInfoSp.getString(NAME, "");
		info.ico = mUserInfoSp.getString(ICO, "");
		info.tel = mUserInfoSp.getString(TEL, "");
		info.account = mUserInfoSp.getString(ACCOUNT, "");
		info.pwd = mUserInfoSp.getString(PWD, "");
		info.sex = mUserInfoSp.getInt(SEX, 0);
		info.token = mUserInfoSp.getString(TOKEN, "");
		info.mapurl = mUserInfoSp.getString(MAP_URL, "");
		return info;
	}

	/**
	 * 获取登录账号密码
	 */
	public UserInfo getLoginUserInfo() {
		UserInfo info = new UserInfo();
		info.tel = mUserInfoSp.getString(TEL, "");
		info.pwd = mUserInfoSp.getString(PWD, "");
		info.name = mUserInfoSp.getString(NAME, "");
		return info;
	}

	/**
	 * 设置用户id
	 * 
	 * @param data
	 */
	public void setUserId(int data) {
		mUserInfoSp.edit().putInt(USER_ID, data).commit();
	}

	/**
	 * 设置用户头像
	 * 
	 * @param ico
	 */
	public void setIco(String ico) {
		mUserInfoSp.edit().putString(ICO, ico).commit();
	}

	/**
	 * 设置用户名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		mUserInfoSp.edit().putString(NAME, name).commit();
	}

	/**
	 * 获取用户id
	 */
	public int getUserId() {
		String id = mUserInfoSp.getString(USER_ID, "0");
		return Integer.valueOf(id);
	}
	
	/**
	 * 获取用户名称
	 */
	public String getName() {
		return mUserInfoSp.getString(NAME, "");
	}

	/**
	 * 获取用户访问token
	 */
	public String getToken() {
		return mUserInfoSp.getString(TOKEN, "");
	}

	/**
	 * 设置用户信息
	 */
	public void setToken() {
		mUserInfoSp.edit().putString(TOKEN, "").commit();
	}


	/**
	 * 手机号
	 */
	public String getTel() {
		return mUserInfoSp.getString(TEL, "");
	}


	/**
	 * 密码
	 */
	public String getPwd() {
		return mUserInfoSp.getString(PWD, "");
	}

	/**
	 * 密码
	 */
	public void setPwd(String pwd) {
		mUserInfoSp.edit().putString(PWD, pwd).commit();
	}

	/**
	 * 头像
	 */
	public String getHeadIcon() {
		return mUserInfoSp.getString(ICO, "");
	}

	/**
	 * 根据key来获取数据
	 */
	public String getStringDataByKey(String key) {
		return mUserInfoSp.getString(key, "");
	}

	/**
	 * 根据key来获取数据
	 */
	public int getIntDataByKey(String key) {
		return mUserInfoSp.getInt(key, 0);
	}

	/**
	 * 根据key设置数据
	 */
	public void setStringDataByKey(String key, String value) {
		mUserInfoSp.edit().putString(key, value).commit();
	}

	/**
	 * 根据key设置数据
	 */
	public void setIntDataByKey(String key, int value) {
		mUserInfoSp.edit().putInt(key, value).commit();
	}

	public void removeUserInfo() {
		sInstance.clear();
		sInstance.mEditor.commit();
	}
}
