package com.infzm.slidingmenu.demo.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.infzm.slidingmenu.demo.MainActivity;
import com.infzm.slidingmenu.demo.NoteActivity;
import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.extra.MySharedPreferences;
import com.infzm.slidingmenu.extra.NoteLab;
/**
 * @date 2014/11/14
 * @author wuwenjie
 * @description 侧边栏菜单
 */
public class LeftFragment extends Fragment implements OnClickListener{
	private View allNote;
	private View newnote;
	private View recover;
	private View mySetting;
	private TextView myLogin;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_menu, null);

		findViews(view);
		
		return view;
	}
	
	
	public void findViews(View view) {
		allNote = view.findViewById(R.id.allnote);
		newnote = view.findViewById(R.id.newnote);
		recover = view.findViewById(R.id.recover);
		mySetting = view.findViewById(R.id.MySettings);
		myLogin = (TextView) view.findViewById(R.id.txt_left_login);
		
		if(MySharedPreferences.isLogin(getActivity())){
			myLogin.setText("已登录");
		}
		allNote.setOnClickListener(this);
		newnote.setOnClickListener(this);
		recover.setOnClickListener(this);
	    mySetting.setOnClickListener(this);
	    myLogin.setOnClickListener(this);
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		Fragment newContent = null;
		String title = null;
		switch (v.getId()) {
		case R.id.allnote: // 所有笔记
			newContent = new NotelistFragment();
			title = getString(R.string.today);
			break;
		case R.id.newnote:
			try{
				NoteLab.isNewNote = true;
				Intent intent = new Intent(getActivity(),NoteActivity.class);
				startActivity(intent);
			}catch(Exception e){
				Toast.makeText(getActivity(), e.toString(), 2*1000).show();
			}
			break;
		case R.id.recover:// 回收站
			newContent = new RecoverFragment();
			title = getString(R.string.lastList);
			break;
		case R.id.MySettings: // 设置
			newContent = new MySettingsFragment();
			title = getString(R.string.settings);
			break;
		case R.id.txt_left_login:
			if(!MySharedPreferences.isLogin(getActivity())){
			newContent = new LoginFragment();
			title = getString(R.string.login);
			}
			break;
		default:
			break;
		}
		if (newContent != null) {
			switchFragment(newContent, title);
		}
	}
	
	/**
	 * 切换fragment
	 * @param fragment
	 */
	private void switchFragment(Fragment fragment, String title) {
		if (getActivity() == null) {
			return;
		}
		if (getActivity() instanceof MainActivity) {
			MainActivity fca = (MainActivity) getActivity();
			fca.switchConent(fragment, title);
		}
	}
	
}
