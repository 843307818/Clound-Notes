package com.infzm.slidingmenu.extra;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class NoteSqlConnect {
	
	public static String objectId;
	
    public static void saveNote(Note mCrime,final Context context){//保存笔记
    	Note crime = new Note(); 	
    	crime.setmDetails(mCrime.getmDetails());
    	crime.setTitle(mCrime.getTitle());
    	crime.setDate(mCrime.getDate());
    	if(MySharedPreferences.isLogin(context)){
    		crime.setName(MySharedPreferences.getSharePreUserName(context));
    		//Toast.makeText(context,MySharedPreferences.getSharePreUserName(context) , 2*1000).show();
    	}else{
    		Toast.makeText(context, "您还未登录...", 2*1000).show();
    		return;
    	}
    	
    	crime.setId(mCrime.getId());
    	crime.setSaved(true);
    	crime.save(context, new SaveListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context,"保存成功",2*1000).show();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, arg1, 3*1000).show();
			}
		});
    	objectId=crime.getObjectId();
    }
    
	public static void queryNote(String name,final Context context){
		
        BmobQuery<Note> query=new BmobQuery<Note>();
	     query.addWhereEqualTo("name",name);//增加查询条件
	     query.findObjects(context, new FindListener<Note>() {

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, arg1,20*1000).show();
			}
			
			@Override
			public void onSuccess(List<Note> list) {
				
				for(int i=0;i<list.size();i++){
					boolean flag = false;
					for(Note s:NoteLab.get(context).getNotes()){
						if(s.getTitle().equals(list.get(i).getTitle())){
							flag = true;
							break;
						}
					}
					if(!flag){
						NoteLab.get(context).addNote(list.get(i));
					}
				}
			}
			
	     });
	}
	
	public static void updateNote(Note crime,final Context context){
    	Note user =new Note();
    	user.setObjectId(crime.getObjectId());
    	user.setmDetails(crime.getmDetails());
    	user.setDelete(crime.isDelete());
    	user.setTitle(crime.getTitle());
    	user.update(context, new UpdateListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "已保存",3*1000).show();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, arg1,3*1000).show();
			}
		});
	}
	
	public static void removeNote(String id,final Context context){
    	Note user = new Note();
    	user.setObjectId(id);
    	user.delete(context, new DeleteListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(context, "已删除",2*1000).show();
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, arg1,2*1000).show();
			}
		});
	}

}
