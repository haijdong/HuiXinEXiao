package com.synjones.huixinexiao.common_base.base.model;

/**
 * 用户实体
 * 
 * @author liangzx
 * @version 1.0
 */
public class UserInfo {

	/**
	 * 用户id
	 */
	public String userId;
	/**
	 * 电话
	 */
	public String tel;
	/**
	 * 账号
	 */
	public String account;
    /**
     * 密码
     */
	public String pwd;
    /**
     * 名称
     */
	public String name;
    /**
     * 头像
     */
	public String ico;
    /**
     * 邮箱
     */
	public String email;
	/**
	 * 0：保密，1：男，2：女
	 */
	public int sex;
    /**
     * 登录会话
     */
	public String token;
	/**
	 * 地区名称
	 */
	public String district_name;
	/**
	 * 地区名称
	 */
	public String district_code;

	/**
	 * 地图连接
	 */
	public String mapurl;
	/**
	 * 默认站点
	 */
	public String def_grid_codes;
	/**
	 * 默认站点名称
	 */
	public String def_grid_codenames;

}
