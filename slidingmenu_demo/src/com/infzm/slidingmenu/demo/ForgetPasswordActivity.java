package com.infzm.slidingmenu.demo;


import com.infzm.slidingmenu.extra.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordByEmailListener;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPasswordActivity extends FragmentActivity{
	
	private EditText editForgetPassword;
	private Button btnForgetPassword;
	private User user;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(arg0);
		setContentView(R.layout.activity_forget_password);
		initView();
		initListen();
	}
	
	
	private void initView(){
		user = new User();
		editForgetPassword = (EditText) findViewById(R.id.edit_forget_password);
		btnForgetPassword = (Button) findViewById(R.id.btn_forget_password);
	}
	
	private void initListen(){
		btnForgetPassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(!editForgetPassword.getText().toString().equals("")){
					

					
					String email = editForgetPassword.getText().toString();
					//String email = "2715815264@qq.com";
					BmobUser.resetPasswordByEmail(getApplicationContext(), email, new
							ResetPasswordByEmailListener() {
								
								@Override
								public void onSuccess() {
									// TODO Auto-generated method stub
									
									//关闭输入法
									InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
									m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
									
									tips("密码修改邮件已发送至您的邮箱，请查收");
									
									finish();
								}
								
								@Override
								public void onFailure(int arg0, String arg1) {
									// TODO Auto-generated method stub
									tips("邮件发送失败，"+arg1);
								}
							});
				}else{
					tips("邮箱不能为空");
				}
			}
		});
	}
	
	private void tips(String str){
		Toast.makeText(getApplicationContext(), str, 2*1000).show();
	}

}
