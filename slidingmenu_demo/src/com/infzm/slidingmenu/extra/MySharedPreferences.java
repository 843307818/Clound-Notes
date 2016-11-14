package com.infzm.slidingmenu.extra;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
	
	static SharedPreferences pref;
	static SharedPreferences.Editor editor;
	
	public  static String getSharePreUserName(Context context){
		pref = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
		String username = pref.getString("username", "");
		return username;
		
	}
	
	public static void putSharePreUserName(Context context,String username){
		editor = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE).edit();
		editor.putString("username", username);
		editor.commit();
	}
	
	public static boolean isLogin(Context context){
		pref = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
		if(pref.getBoolean("isLogin", false)){
			return true;
		}else{
			return false;
		}
	}
	
	public static void setLogin(Context context){
		editor = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE).edit();
		editor.putBoolean("isLogin", true);
		editor.commit();
	}
	
	public static void setExit(Context context){
		editor = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE).edit();
		editor.putBoolean("isLogin", false);
		editor.commit();
	}
	
}
