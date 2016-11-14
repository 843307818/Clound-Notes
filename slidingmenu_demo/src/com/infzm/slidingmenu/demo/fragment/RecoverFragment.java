package com.infzm.slidingmenu.demo.fragment;

import java.util.ArrayList;
import java.util.List;

import com.infzm.slidingmenu.demo.R;
import com.infzm.slidingmenu.extra.Note;
import com.infzm.slidingmenu.extra.NoteLab;
import com.infzm.slidingmenu.extra.NoteSqlConnect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
/**
 * 
 * @author wuwenjie
 * @description 今日
 */
public class RecoverFragment extends Fragment {
	
	private ListView listView;
	private ArrayList<String> datalist;
	private ArrayList<Note> notelist;
	private ArrayAdapter<String> arrayAdapter;
	
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
		View view = inflater.inflate(R.layout.frag_recoverlist, null);
		initView(view);
		initListen(view);
		return view;
	}
	
	private void initView(View view){
		 datalist = new ArrayList<String>();
		 listView = (ListView) view.findViewById(R.id.list_recover_view);
	     registerForContextMenu(listView);
	     arrayAdapter = new ArrayAdapter<String>(getActivity(),
	     android.R.layout.simple_list_item_1,getData());
	     listView.setAdapter(arrayAdapter);
		
    }
	private List<String> getData(){
		
	    //listView.setAdapter(null);
	    
		datalist = new ArrayList<String>();
		notelist = NoteLab.get(getActivity()).getNotes();
			for(int i = 0;i <notelist.size();i++) {  
				if(notelist.get(i).isDelete()){
					datalist.add(notelist.get(i).getTitle().toString()+"");
				}
			}
		    return datalist;
		
	}
	
	private void initListen(View view){
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		getActivity().getMenuInflater().inflate(R.menu.recover_note_list_item_context, menu);
	};
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int position = info.position;
		//ArrayAdapter<String> adapter =(ArrayAdapter<String>)getListAdapter();
		String s = arrayAdapter.getItem(position);
		switch(item.getItemId()){
		case R.id.menu_item_recover_note:
			Note note1 = NoteLab.get(getActivity()).findNameNote(s);
			datalist.remove(s);
			arrayAdapter.notifyDataSetChanged();
			NoteSqlConnect.removeNote(note1.getObjectId(), getActivity());
			note1.setDelete(true);
			NoteLab.get(getActivity()).updateNote(note1);
			NoteSqlConnect.updateNote(note1, getActivity());
			return true;
		case R.id.menu_item_recover_delete_note:
			Note note = NoteLab.get(getActivity()).deleteNameNote(s);
			datalist.remove(s);
			arrayAdapter.notifyDataSetChanged();
			NoteSqlConnect.removeNote(note.getObjectId(), getActivity());
			note.setDelete(true);
			NoteLab.get(getActivity()).updateNote(note);
			NoteSqlConnect.updateNote(note, getActivity());
			return true;
		}
		return super.onContextItemSelected(item);
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
