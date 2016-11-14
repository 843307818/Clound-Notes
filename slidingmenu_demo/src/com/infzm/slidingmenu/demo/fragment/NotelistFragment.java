package com.infzm.slidingmenu.demo.fragment;

import java.util.ArrayList;
import java.util.List;

import com.infzm.slidingmenu.demo.MainActivity;
import com.infzm.slidingmenu.demo.NoteActivity;
import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.extra.LocalNoteSqlConnect;
import com.infzm.slidingmenu.extra.Note;
import com.infzm.slidingmenu.extra.NoteLab;
import com.infzm.slidingmenu.extra.NoteSqlConnect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
/**
 * 
 * @author wuwenjie
 * @description 今日
 */
public class NotelistFragment extends Fragment {
	private ListView listView;
	public static ArrayList<String> datalist;
	ArrayList<Note> notelist;
	public static ArrayAdapter<String> arrayAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//NoteSqlConnect.queryNote("15255362527",getActivity());
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.frag_notelist, null);
	     listView = (ListView)view.findViewById(R.id.list_view);
	     registerForContextMenu(listView);
	     arrayAdapter = new ArrayAdapter<String>(getActivity(),
	     android.R.layout.simple_list_item_1,getData());
	     listView.setAdapter(arrayAdapter);
	     listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				//String data = datalist.get(arg2);			
				Intent intent = new Intent(getActivity(),NoteActivity.class);
				NoteLab.isNewNote = false;
				NoteLab.noteID = notelist.get(arg2).getId();
				startActivity(intent);
			    Log.d(getTag(),notelist.get(arg2).getId().toString());
			}
		});
		return view;
	}
	private List<String> getData(){
	//datalist.clear();
	//arrayAdapter.notifyDataSetChanged();
    listView.setAdapter(null);
    
	datalist = new ArrayList<String>();
	notelist = NoteLab.get(getActivity()).getNotes();
		for(int i = 0;i <notelist.size();i++) {
			if(!notelist.get(i).isDelete()){
			datalist.add(notelist.get(i).getTitle().toString()+"");
			}
		}
	    return datalist;
	 }
	
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		getActivity().getMenuInflater().inflate(R.menu.note_list_item_context, menu);
	};
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int position = info.position;
		//ArrayAdapter<String> adapter =(ArrayAdapter<String>)getListAdapter();
		String s = arrayAdapter.getItem(position);
		switch(item.getItemId()){
		case R.id.menu_item_delete_crime:
			Note note = NoteLab.get(getActivity()).deleteNameNote(s);
			datalist.remove(s);
			arrayAdapter.notifyDataSetChanged();
			//NoteSqlConnect.removeNote(note.getObjectId(), getActivity());
			note.setDelete(true);
			NoteLab.get(getActivity()).updateNote(note);
			NoteSqlConnect.updateNote(note, getActivity());
			return true;
		}
		return super.onContextItemSelected(item);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
