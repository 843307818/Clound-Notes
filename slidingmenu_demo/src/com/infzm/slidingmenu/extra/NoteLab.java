package com.infzm.slidingmenu.extra;

import java.util.ArrayList;
import java.util.UUID;
import android.content.Context;

public class NoteLab {
	private ArrayList<Note> mNotes;
	private static NoteLab sNoteLab;
	private Context mAppContext;
	public static UUID noteID;//记录笔记的UUID
	public static boolean isNewNote=false;//用于区别是否是新建笔记
	public static Note note;
	private NoteLab(Context appContext){
		mAppContext = appContext;
		mNotes = new ArrayList<Note>();
//		for(int i =0;i<5; i++){
//			Note n = new Note();
//			//n.setName("15255362527");
//			n.setTitle("android#"+i);
//			n.setmDetails("Mynote");
//			n.setDate("2016-10-02 17:23:44");
//			mNotes.add(n);
//		}
	}
	
	public static NoteLab get(Context c){
		if(sNoteLab == null){
			sNoteLab = new NoteLab(c.getApplicationContext());
		}
		return sNoteLab;
	}
	
	public ArrayList<Note> getNotes(){
		return mNotes;
	}
	public Note getNote(UUID id){
		for(Note n : mNotes){
			if(n.getId().equals(id))
				return n;
		}
		return null;
	}
	
    public void addNote(Note c) {
        mNotes.add(c);
    }
    
    public void updateNote(Note c){
		for(Note n : mNotes){
			if(n.getId().equals(c.getId())){
				n.setmDetails(c.getmDetails());
				n.setTitle(c.getTitle());
				n.setDelete(c.isDelete());
				break;
			}
				
		}
    }

    
    public Note deleteNameNote(String c){
    	for(Note n:mNotes){
    		if(n.getTitle().equals(c)){
    			mNotes.remove(n);
    			return n;
    		}
    	}
    	return null;
    }
    
    public Note findNameNote(String c){
    	for(Note n:mNotes){
    		if(n.getTitle().equals(c)){
    			return n;
    		}
    	}
    	return null;
    }

    public void deleteNote(Note c) {
        mNotes.remove(c);
    }
}
