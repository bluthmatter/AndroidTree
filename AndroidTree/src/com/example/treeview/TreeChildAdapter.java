package com.example.treeview;

import java.util.List;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TreeChildAdapter extends BaseAdapter implements OnClickListener{

	List<TreeModel> list;
	Context context;
	TreeFragment treeFragment;
	
	public TreeChildAdapter(Fragment fragment, Context context, List<TreeModel> list) {
		this.list = list;
		this.context = context;
		this.treeFragment = (TreeFragment) fragment;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView textView = new TextView(context);
		textView.setText(list.get(position).name);
		textView.setTag(list.get(position).code);
		textView.setTextColor(0xffffffff);
		textView.setOnClickListener(this);
		return textView;
	}

	@Override
	public void onClick(View v) {
		List<TreeModel> list = treeFragment.getCurTreeList(v.getTag().toString());
		if(list.size() == 0)
			return;
		
		ListView listview = new ListView(context);
		TreeChildAdapter tca = new TreeChildAdapter(treeFragment, context, list);
		listview.setAdapter(tca);
		
		//删除当前的listview之后的listview
		for(int i = 0; i < treeFragment.tpAdapter.list.size(); i++) {
				if(i > treeFragment.mCurrentPageIndex) {
					treeFragment.tpAdapter.list.remove(i);
				}
		}
		treeFragment.tpAdapter.list.add(treeFragment.tpAdapter.list.size() ,listview);
		treeFragment.tpAdapter.notifyDataSetChanged();
		treeFragment.mPager.setCurrentItem(treeFragment.tpAdapter.list.size()- 1, true);
	}

}
