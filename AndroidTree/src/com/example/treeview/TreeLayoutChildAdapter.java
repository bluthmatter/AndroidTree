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

public class TreeLayoutChildAdapter extends BaseAdapter implements OnClickListener{

	List<TreeModel> list;
	Context context;
	TreeLayoutFragment treeFragment;
	
	public TreeLayoutChildAdapter(Fragment fragment, Context context, List<TreeModel> list) {
		this.list = list;
		this.context = context;
		this.treeFragment = (TreeLayoutFragment) fragment;
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
		TreeLayoutChildAdapter tca = new TreeLayoutChildAdapter(treeFragment, context, list);
		listview.setAdapter(tca);
	
		for(int i = 0; i < treeFragment.mPager.getChildCount(); i++) {
			if(i > treeFragment.mPager.getCurScreen()) {
				treeFragment.mPager.removeViewAt(i);
				treeFragment.mPager.invalidate();
			}
		}
		
		treeFragment.mPager.addView(listview);
		treeFragment.mPager.snapToScreen(treeFragment.mPager.getChildCount() - 1);
		treeFragment.mCurrentPageIndex++;
	}

}
