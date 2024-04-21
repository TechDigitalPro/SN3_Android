package com.rke.sn3.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;


import java.util.Locale;
import java.util.Map;

public class SN3Application extends Application {

    public static SN3Application Me;
	private SN3DB mDb;


	private Location location;

    public String token;
	public String customerId;
	public Integer badgeCount;
    public Map<String, String> mPrefs;


	public SN3DB getDb() { return mDb; }
	public String getPrefString(String key, String defValue){
		return mPrefs.containsKey(key) ? mPrefs.get(key) : defValue;
	}
	public int getPrefInt(String key, int defValue){
		return mPrefs.containsKey(key) ? Util.getInt(defValue, mPrefs.get(key)) : defValue;
	}
	public long getPrefLong(String key, long defValue){
		return mPrefs.containsKey(key) ? Util.getLong(defValue, mPrefs.get(key)) : defValue;
	}
	public boolean getPrefBool(String key, boolean defValue){
		return mPrefs.containsKey(key) ? Util.getBool(defValue, mPrefs.get(key)) : defValue;
	}
	public void putPref(String key, Object value){
		mPrefs.put(key, value.toString());
		if (mDb != null) {
			mDb.insertPref(key, mPrefs.get(key));
		}
	}
	public static void setLocaleSa (Activity activity){
		Locale locale = new Locale(SN3Application.Me.getPrefString(Constant.PREF_LANG, Constant.DEF_LANG));
		Locale.setDefault(locale);
		Resources resources = activity.getResources();
		Configuration config = resources.getConfiguration();
		config.setLocale(locale);
		resources.updateConfiguration(config, resources.getDisplayMetrics());
	}
	public String getLocale(){
		Locale locale = Locale.getDefault();
		return locale.toString();
	}
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
    @Override
    public void onCreate() {
        super.onCreate();
        Me = this;

		mDb = new SN3DB(getApplicationContext());
        mPrefs = mDb.listPrefs();
		token = getPrefString(Constant.user_token, "");
		location = new Location("");
		getLocale();
    }




}
