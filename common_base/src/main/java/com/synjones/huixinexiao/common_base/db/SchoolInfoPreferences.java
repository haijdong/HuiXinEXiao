package com.synjones.huixinexiao.common_base.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.synjones.huixinexiao.common_base.app.BaseApplication;
import com.synjones.huixinexiao.common_base.base.model.Schoolinfo;


/**
 * 保存当前登录用户基本信息
 * 
 * @author liangzx
 * @version 1.0
 */
public class SchoolInfoPreferences {

	/**
	 * 学校名称
	 */
	private static final String SCHOOL_NAME = "schoolName";
	/**
	 * 学校ID
	 */
	private static final String SCHOOL_ID = "schoolId";

	/**
	 * UserInfoPreferences单例
	 */
	private static SchoolInfoPreferences sInstance;

	/**
	 * 文件编辑器
	 */
	private Editor mEditor;

	/**
	 * 用户信息SharedPreferences
	 */
	private SharedPreferences mSchoolConfigSp;

	/**
	 * 文件名
	 */
	private final String FILE_NAME = "school_config";

	/**
	 * 构造
	 *
	 * @param context
	 *            Context上下文
	 */
	public SchoolInfoPreferences(Context context) {
		mSchoolConfigSp = context.getSharedPreferences(FILE_NAME,
				Context.MODE_PRIVATE);
		mEditor = mSchoolConfigSp.edit();
	}

	/**
	 * 获取UserInfoPreferences单例
	 * @return UserInfoPreferences单例
	 */
	public static SchoolInfoPreferences getInstance() {
		if (sInstance == null) {
			sInstance = new SchoolInfoPreferences(BaseApplication.getIns());
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
	public void setShoolInfo(Schoolinfo data) {
		mSchoolConfigSp.edit().putString(SCHOOL_NAME, data.schoolName)
				.putInt(SCHOOL_ID, data.schoolId)
				.commit();
	}


	/**
	 * 获取登录账号密码
	 */
	public Schoolinfo getSchoolInfo() {
		Schoolinfo info = new Schoolinfo();
		info.schoolName = mSchoolConfigSp.getString(SCHOOL_NAME, "");
		info.schoolId = mSchoolConfigSp.getInt(SCHOOL_ID, 0);
		return info;
	}




	/**
	 * 根据key来获取数据
	 */
	public String getStringDataByKey(String key) {
		return mSchoolConfigSp.getString(key, "");
	}

	/**
	 * 根据key来获取数据
	 */
	public int getIntDataByKey(String key) {
		return mSchoolConfigSp.getInt(key, 0);
	}

	/**
	 * 根据key设置数据
	 */
	public void setStringDataByKey(String key, String value) {
		mSchoolConfigSp.edit().putString(key, value).commit();
	}

	/**
	 * 根据key设置数据
	 */
	public void setIntDataByKey(String key, int value) {
		mSchoolConfigSp.edit().putInt(key, value).commit();
	}

	public void removeSchoolInfo() {
		sInstance.clear();
		sInstance.mEditor.commit();
	}
}
