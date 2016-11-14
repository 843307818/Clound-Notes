package com.infzm.slidingmenu.demo;

import java.util.UUID;

import com.infzm.slidingmenu.demo.fragment.NotelistFragment;
import com.infzm.slidingmenu.extra.Note;
import com.infzm.slidingmenu.extra.NoteLab;
import com.infzm.slidingmenu.extra.NoteSqlConnect;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends FragmentActivity{
	private TextView btnSave;
	public EditText editTitle;
	public EditText editDetails;
	public TextView txtNewNoteTime;
	public Note mNote;
	public String notFixTiTle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		mNote = new Note();
		if(!NoteLab.isNewNote){
		    mNote = NoteLab.get(getApplicationContext()).getNote(NoteLab.noteID);
		}
		txtNewNoteTime =(TextView) findViewById(R.id.txt_newnote_time);
		editTitle = (EditText) findViewById(R.id.edit_title);
		editDetails = (EditText) findViewById(R.id.edit_details);
		btnSave = (TextView) findViewById(R.id.txt_top_save);
		txtNewNoteTime.setText(mNote.getDate());
		if(!NoteLab.isNewNote){
			notFixTiTle=mNote.getTitle();
			editTitle.setText(mNote.getTitle());
			editDetails.setText(mNote.getmDetails());
		}else{
			editTitle.setText("");
			editDetails.setText("");
		}

		
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				mNote.setTitle(editTitle.getText().toString());
				mNote.setmDetails(editDetails.getText().toString());
				if(!mNote.getTitle().equals("") && NoteLab.isNewNote){
					NoteLab.isNewNote = false;//保存后就不是新建的了
					NoteSqlConnect.saveNote(mNote, getApplicationContext());
					NoteLab.get(getApplicationContext()).addNote(mNote);
					NotelistFragment.datalist.add(mNote.getTitle());
				}else if(!mNote.getTitle().equals("") && !NoteLab.isNewNote){
					NoteSqlConnect.updateNote(mNote, getApplicationContext());
				    NoteLab.get(getApplicationContext()).updateNote(mNote);
				    int i=0;
				    for(String s:NotelistFragment.datalist){//更新列表
				    	if(s.equals(notFixTiTle)){
				    		NotelistFragment.datalist.set(i, mNote.getTitle());
				    		break;
				    	}
				    	i++;
				    }
				}else{
					Toast.makeText(getApplicationContext(), "标题不能为空", 2*1000).show();
				}
				NotelistFragment.arrayAdapter.notifyDataSetChanged();
			}
		});
	}
	
	
	
}
