package com.infzm.slidingmenu.demo;

import cn.bmob.v3.listener.SaveListener;

import com.infzm.slidingmenu.extra.MySharedPreferences;
import com.infzm.slidingmenu.extra.User;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends FragmentActivity{
	
	private Button btnRegister; 
	private EditText editUserName;
	private EditText editPassword;
	private EditText editInsurePassword;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(arg0);
		setContentView(R.layout.activity_register);
		initView();
		initLister();
	}
	
	private void initView(){
		btnRegister = (Button)findViewById(R.id.btn_register_register);
		editUserName = (EditText)findViewById(R.id.edit_register_username);
		editPassword = (EditText)findViewById(R.id.edit_register_password);
		editInsurePassword = (EditText)findViewById(R.id.edit_register_insurepassword);
	}
	
	private void initLister(){
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!editUserName.getText().toString().equals("") && !editPassword.getText().toString().equals("") && 
						!editInsurePassword.getText().equals("")){
					if(editPassword.getText().toString().equals(editInsurePassword.getText().toString())){
						User user = new User();
						user.setUsername(editUserName.getText().toString());
						user.setPassword(editPassword.getText().toString());
						user.setEmail(editUserName.getText().toString());
						user.setEmailVerified(true);
						user.signUp(getApplicationContext(), new SaveListener() {
							
							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								
								//如果输入法打开则关闭，如果 关闭则打开
								InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
								m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
								
								tips("验证信息已发送您的邮箱，请验证后登录");
								
								finish();
							}
							
							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub
								tips("注册失败，"+arg1);
							}
						});
					}else{
						tips("确认密码与密码不一致！");
					}
					
				}else{
					tips("邮箱或密码不能为空！");
				}

			}
		});
	}
	
	private void tips(String str){
		Toast.makeText(getApplicationContext(), str, 2*1000).show();
	}
}
