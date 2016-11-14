package com.infzm.slidingmenu.extra;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import cn.bmob.v3.BmobObject;

public class Note extends BmobObject{
	private String name;
    private UUID mId;
    private String mTitle;
    private String mDateStr;
    private Date mDate;
    private String mDetails;
    private boolean isSaved;
    private boolean isDelete;
    
    public Note() {
        mId = UUID.randomUUID();
		mDate = new Date();
		//DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//DateFormat df = new SimpleDateFormat("HH:mm");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		this.mDateStr = df.format(mDate);
		this.isSaved = false;
		this.isDelete = false;
    }

    public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}

	@Override
    public String toString() {
        return mTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    
    public void setId(UUID mId){
    	this.mId=mId;
    }

    public UUID getId() {
        return mId;
    }

    public void setDate(String mDateStr){
    	this.mDateStr=mDateStr;
    	
    }
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
        return mDateStr;
    }

	public String getmDetails() {
		return mDetails;
	}

	public void setmDetails(String mDetails) {
		this.mDetails = mDetails;
	}
    
    




}
