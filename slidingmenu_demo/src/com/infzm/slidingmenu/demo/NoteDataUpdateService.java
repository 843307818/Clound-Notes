package com.infzm.slidingmenu.demo;

import com.infzm.slidingmenu.extra.LocalNoteSqlConnect;
import com.infzm.slidingmenu.extra.MyDatabaseHelper;
import com.infzm.slidingmenu.extra.MySharedPreferences;
import com.infzm.slidingmenu.extra.NoteLab;
import com.infzm.slidingmenu.extra.NoteSqlConnect;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.widget.Toast;

public class NoteDataUpdateService extends Service{
	
	private Context mContext;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//创建SQLite本地数据库
		mContext = getApplicationContext();
		if(MySharedPreferences.isLogin(getApplicationContext())){
			NoteSqlConnect.queryNote(MySharedPreferences.getSharePreUserName(getApplicationContext()),getApplicationContext());
			LocalNoteSqlConnect.createDatabase(mContext);
		    LocalNoteSqlConnect.queryData(getApplicationContext());
		}

				
		//Toast.makeText(getApplicationContext(), "Service is Start", 2*1000).show();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		//Toast.makeText(getApplicationContext(), "调用OnstartCommand", 2*1000).show();
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(getApplicationContext(), "Service is destroy", 2*1000).show();
	}

}
