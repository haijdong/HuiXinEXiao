package com.synjones.huixinexiao.common_base.base.model;

import org.json.JSONException;
import org.json.JSONObject;

public class VersionBean {

	public int id;
	public String version_name;
	public int version_num;
	public String version_time;
	public String description;
	public String url;
	public String dir_url;
	public boolean isForce;
	public int must_be_updated;
	public long total_size;
	public String school_id;

	// "id": 2,
	// "version_name": "3",
	// "version_num": 33,
	// "version_time": 1490003519000,
	// "description": "3333",
	// "url":
	// "http://123.57.54.215:8080/download/8b312807-c4e0-4ef3-933e-1a6d0f367a4c.apk",
	// "dir_url":
	// "/opt/download//8b312807-c4e0-4ef3-933e-1a6d0f367a4c.apk",
	// "must_be_updated": 0,
	// "total_size": 16291826,
	public VersionBean(JSONObject json) {
		if (json == null)
			return;

		try {
			if (json.has("id"))
				id = json.getInt("id");
			if (json.has("version_name"))
				version_name = json.getString("version_name");
			if (json.has("version_num"))
				version_num = json.getInt("version_num");
			if (json.has("version_time"))
				version_time = json.getString("version_time");
			if (json.has("description"))
				description = json.getString("description");
			if (json.has("url"))
				url = json.getString("url");
			if (json.has("dir_url"))
				dir_url = json.getString("dir_url");
			if (json.has("total_size"))
				total_size = json.getLong("total_size");
			if (json.has("school_id"))
				school_id = json.getString("school_id");
			if (json.has("must_be_updated"))
				must_be_updated = json.getInt("must_be_updated");

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getVersionName() {
		return version_name;
	}

	public int getVersionCode() {
		return version_num;
	}

	public String getDescription() {
		return description;
	}

	public String getApkUrl() {
		return url;
	}

	public boolean isForce() {
		if (this.must_be_updated == 1) {
			isForce = true;
		} else {
			isForce = false;
		}
		return isForce;
	}

	public long getTotal_size() {
		return total_size;
	}

}
