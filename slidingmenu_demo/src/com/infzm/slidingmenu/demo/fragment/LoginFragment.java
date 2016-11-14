package com.infzm.slidingmenu.demo.fragment;

import cn.bmob.v3.listener.SaveListener;

import com.infzm.slidingmenu.demo.ForgetPasswordActivity;
import com.infzm.slidingmenu.demo.MainActivity;
import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.demo.R.id;
import com.infzm.slidingmenu.demo.R.layout;
import com.infzm.slidingmenu.demo.RegisterActivity;
import com.infzm.slidingmenu.extra.MySharedPreferences;
import com.infzm.slidingmenu.extra.User;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author wuwenjie
 * @description 今日
 */
public class LoginFragment extends Fragment {
	
	private Button btnLogin;
	private Button btnRegister;
	private EditText editUserName;
	private EditText editPassword;
	private TextView txtForgetPassword;
	private TextView txtUserNameChange;
	private User user;
	
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
		View view = inflater.inflate(R.layout.frag_mylogin, null);
		initView(view);
		initLister();
		return view;
	}
	private void initView(View view){
		user =  new User();
		btnRegister = (Button) view.findViewById(R.id.btn_myregister);
		btnLogin = (Button) view.findViewById(R.id.btn_mylogin);
		editUserName = (EditText) view.findViewById(R.id.edit_username);
		editPassword = (EditText) view.findViewById(R.id.edit_password);
		txtForgetPassword = (TextView) view.findViewById(R.id.txt_myfindpassword);
		//txtUserNameChange =(TextView) view.findViewById(R.id.txt_left_login);
	}
	
	private void initLister(){
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent =  new Intent(getActivity(),RegisterActivity.class);
				startActivity(intent);
			}
		});
		
		txtForgetPassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent mIntent  = new Intent(getActivity(),ForgetPasswordActivity.class);
				startActivity(mIntent);
				
			}
		});
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!editUserName.getText().toString().equals("") && !editPassword.getText().toString().equals("")){
					user.setUsername(editUserName.getText().toString());
					user.setPassword(editPassword.getText().toString());
					user.login(getActivity(), new SaveListener() {
						
						@Override
						public void onSuccess() {
							// TODO Auto-generated method stub
							
							//如果输入法打开则关闭，如果 关闭则打开
							InputMethodManager m=(InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
							m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
							
							if(user.getEmailVerified()){
								Fragment newContent = null;
								String title = null;
								newContent = new NotelistFragment();
								title = getString(R.string.today);
								switchFragment(newContent, title);
								//txtUserNameChange.setText("已登录");
								
								MySharedPreferences.putSharePreUserName(getActivity(), editUserName.getText().toString());
								MySharedPreferences.setLogin(getActivity());
								
								tips("登录成功");
							}else{
								tips("登录失败，请先验证您的邮箱");
							}
						}
						
						@Override
						public void onFailure(int arg0, String arg1) {
							// TODO Auto-generated method stub
							tips("登录失败，"+arg1);
						}
					});
					
					
				}else{
					tips("用户名或密码不能为空");
				}
			}
		});
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	private void tips(String str){
		Toast.makeText(getActivity(),str, 2*1000).show();
	}
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
