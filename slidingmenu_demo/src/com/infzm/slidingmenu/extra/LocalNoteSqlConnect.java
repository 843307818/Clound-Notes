package com.infzm.slidingmenu.extra;

import java.util.ArrayList;

import com.infzm.slidingmenu.demo.fragment.NotelistFragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class LocalNoteSqlConnect {
	
	private static MyDatabaseHelper dbHelper;
	static ArrayList<Note> notelist;
	
	public static void createDatabase(Context context){
		dbHelper = new MyDatabaseHelper(context, "Notes.db", null, 1);
		dbHelper.getWritableDatabase();
	}
	
	public static void addData(Context context){
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		int j=NoteLab.get(context).getNotes().size();
		Log.d("Loacl","notes长度"+j);
		notelist = NoteLab.get(context).getNotes();
		for(int i=0;i<j;i++){
			//Log.d("Loacl",notelist.get(i).getName().toString());
			Log.d("Loacl",notelist.get(i).getId().toString());
			Log.d("Loacl",notelist.get(i).getTitle().toString());
			Log.d("Loacl",notelist.get(i).getmDetails().toString());
			Log.d("Loacl",notelist.get(i).getDate().toString());
			
			//values.put("name",notelist.get(i).getName());
			values.put("mId",notelist.get(i).getObjectId().toString() );
			values.put("mTitle", notelist.get(i).getTitle());
			values.put("mDetails",notelist.get(i).getmDetails());
			values.put("mDateStr", notelist.get(i).getDate());
			if(notelist.get(i).isDelete()){
				values.put("mIsDelete", 1);
			}else{
				values.put("mIsDelete", 0);
			}
			db.insert("Note", null, values);
			values.clear();
		}
	}
	
	public static void queryData(Context context){
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Note n;
		Cursor cursor = db.query("Note", null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				n = new Note();
				n.setName("15255362527");
				NotelistFragment.datalist.add(cursor.getString(cursor.getColumnIndex("mTitle")));
				n.setObjectId(cursor.getString(cursor.getColumnIndex("mId")));
				n.setTitle(cursor.getString(cursor.getColumnIndex("mTitle")));
				n.setmDetails(cursor.getString(cursor.getColumnIndex("mDetails")));
				n.setDate(cursor.getString(cursor.getColumnIndex("mDateStr")));
				int m=cursor.getInt(cursor.getColumnIndex("mIsDelete"));
				if(m==1){
					n.setDelete(true);
				}else{
					n.setDelete(false);
				}
				String mId = cursor.getString(cursor.getColumnIndex("mTitle"));
				String Title = cursor.getString(cursor.getColumnIndex("mDetails"));
				Log.d("更新", mId);
				Log.d("更新",Title);
				NoteLab.get(context).addNote(n);
			} while (cursor.moveToNext());
		}
		cursor.close();
	}
	
	public static void deleteAllData(Context context){
		SQLiteDatabase db =  dbHelper.getWritableDatabase();
		db.delete("Note", null, null);
	}
}
