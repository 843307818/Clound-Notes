package com.infzm.slidingmenu.demo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;

import com.infzm.slidingmenu.demo.fragment.LoginFragment;
import com.infzm.slidingmenu.demo.fragment.NotelistFragment;
import com.infzm.slidingmenu.demo.fragment.LeftFragment;
import com.infzm.slidingmenu.demo.fragment.RecoverFragment;
import com.infzm.slidingmenu.extra.LocalNoteSqlConnect;
import com.infzm.slidingmenu.extra.MyDatabaseHelper;
import com.infzm.slidingmenu.extra.MySharedPreferences;
import com.infzm.slidingmenu.extra.NoteLab;
import com.infzm.slidingmenu.extra.NoteSqlConnect;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * @date 2014/11/14
 * @author wuwenjie
 * @description 主界面
 */
public class MainActivity extends SlidingFragmentActivity implements
		OnClickListener {

	private ImageView topButton;
	private Fragment mContent;
	private TextView topTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 无标题
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initSlidingMenu(savedInstanceState);
		Bmob.initialize(this, "5f1b3e34ff5b6d3f0896d7acdbfbd64f");//初始化BmobSDK		
//		if(NoteLab.get(getApplicationContext()).getNotes().isEmpty())
//			Toast.makeText(getApplicationContext(), "mNotes is null", 2*1000).show();
		Intent myService = new Intent(this,NoteDataUpdateService.class);//启动服务
		startService(myService);
		
		topButton = (ImageView) findViewById(R.id.topButton);
		topButton.setOnClickListener(this);
		topTextView = (TextView) findViewById(R.id.topTv);
		if(MySharedPreferences.isLogin(getApplicationContext())){
			Fragment fg =new NotelistFragment();//可以在这改初始界面
			switchConent(fg, "所有笔记");
		}else{
			Fragment fg = new LoginFragment();
			switchConent(fg, "登录");
		}
		
	
	}

	/**
	 * 初始化侧边栏
	 */
	private void initSlidingMenu(Bundle savedInstanceState) {
		// 如果保存的状态不为空则得到之前保存的Fragment，否则实例化MyFragment
		if (savedInstanceState != null) {
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		}

		if (mContent == null) {
			mContent = new LeftFragment();
		}

		// 设置左侧滑动菜单
		setBehindContentView(R.layout.menu_frame_left);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.menu_frame, new LeftFragment()).commit();

		// 实例化滑动菜单对象
		SlidingMenu sm = getSlidingMenu();
		// 设置可以左右滑动的菜单
		sm.setMode(SlidingMenu.LEFT);
		// 设置滑动阴影的宽度
		sm.setShadowWidthRes(R.dimen.shadow_width);
		// 设置滑动菜单阴影的图像资源
		sm.setShadowDrawable(null);
		// 设置滑动菜单视图的宽度
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 设置渐入渐出效果的值
		sm.setFadeDegree(0.35f);
		// 设置触摸屏幕的模式,这里设置为全屏
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// 设置下方视图的在滚动时的缩放比例
		sm.setBehindScrollScale(0.0f);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	/**
	 * 切换Fragment
	 * 
	 * @param fragment
	 */
	public void switchConent(Fragment fragment, String title) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, fragment).commit();
		getSlidingMenu().showContent();
		topTextView.setText(title);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.topButton:
			toggle();
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(!NoteLab.get(getApplicationContext()).getNotes().isEmpty() && MySharedPreferences.isLogin(getApplicationContext())){
			LocalNoteSqlConnect.deleteAllData(getApplicationContext());
			LocalNoteSqlConnect.addData(getApplicationContext());
			//Toast.makeText(getApplicationContext(), "mNote is notNUll", 2*1000).show();
		}
		//LocalNoteSqlConnect.addData(getApplication());
		//Toast.makeText(getApplicationContext(), "MainActivity is destroy", 2*1000).show();
	}

}
