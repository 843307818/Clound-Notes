package com.infzm.slidingmenu.demo.fragment;

import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.extra.MySharedPreferences;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
/**
 * 
 * @author wuwenjie
 * @description 今日
 */
public class MySettingsFragment extends Fragment {
	
	private Button btnExit;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_settings, null);
		btnExit = (Button) view.findViewById(R.id.btn_exit);
		if(!MySharedPreferences.isLogin(getActivity())){
			btnExit.setEnabled(true);
		}
		btnExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MySharedPreferences.setExit(getActivity());
				getActivity().finish();
			}
		});
		return view;
	}
	
	
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
