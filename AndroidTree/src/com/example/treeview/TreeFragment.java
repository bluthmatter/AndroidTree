package com.example.treeview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TreeFragment extends DialogFragment {

	int mNum;
	List<ListView> childList = new ArrayList<ListView>();
	ViewPager mPager;
	List<TreeModel> list = new ArrayList<TreeModel>();
	String title;
	String result;
	int mCurrentPageIndex;
	TreePagerAdapter tpAdapter;
	
	public TreeFragment(String title) {
		this.title = title;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initTreeData();
	}
	
	private void initTreeData() {
		list.add(new TreeModel("浙江省", "1", ""));
		list.add(new TreeModel("杭州市", "11", "1"));
		list.add(new TreeModel("宁波市", "12", "1"));
		list.add(new TreeModel("湖州市", "13", "1"));
		list.add(new TreeModel("舟山市", "14", "1"));
		list.add(new TreeModel("嘉庆市", "15", "1"));
		list.add(new TreeModel("西湖区", "111", "11"));
		list.add(new TreeModel("上城区", "112", "11"));
		list.add(new TreeModel("滨江区", "113", "11"));
		list.add(new TreeModel("下沉区", "114", "11"));
		list.add(new TreeModel("翠苑街道", "1111", "111"));
		list.add(new TreeModel("中山街道", "1121", "112"));
		list.add(new TreeModel("武林门", "1131", "113"));
		list.add(new TreeModel("海曙区", "121", "12"));
		list.add(new TreeModel("哥廷根", "122", "12"));
		list.add(new TreeModel("gtr给他", "1211", "13"));
		list.add(new TreeModel("分为分", "1212", "13"));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.treeview, container, false);
		mPager = (ViewPager) v.findViewById(R.id.vPager);
		return v;
	}
	
	public String getResult() {
		return result;
	}
	
	@Override
	public void onActivityCreated(Bundle arg0) {
		super.onActivityCreated(arg0);
		this.getDialog().setTitle(title);
		mPager.setOnPageChangeListener(new PagerListener());
		ListView listview = new ListView(getActivity());
//		TreeChildAdapter tcAdapter = new TreeChildAdapter(context, dataList);
		
		
		TreeChildAdapter tca = new TreeChildAdapter(this, getActivity(), getCurTreeList(""));
		listview.setAdapter(tca);
		listview.setCacheColorHint(0x00000000);
		childList.add(listview);
		
		
		tpAdapter = new TreePagerAdapter(mPager, childList);
		mPager.setAdapter(tpAdapter);
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		int width = 300;
		int height = 300;
		getDialog().getWindow().setLayout(width, height);
	}
	
	List<TreeModel> getCurTreeList(String parent) {
		List<TreeModel> rst_list = new ArrayList<TreeModel>();
		for (TreeModel treeModel : list) {
			if(treeModel.parent.equals(parent)) {
				rst_list.add(treeModel);
			}
		}
		return rst_list;
	}
	
	private class PagerListener extends SimpleOnPageChangeListener {
		@Override
		public void onPageSelected(int position) {
			super.onPageSelected(position);
			mCurrentPageIndex = position;
		}
	}
}


class TreeModel {
	String id;
	String name;
	String code;
	String parent;
	
	
	public TreeModel(String name) {
		super();
		this.name = name;
	}

	public TreeModel(String name, String code, String parent) {
		super();
		this.name = name;
		this.code = code;
		this.parent = parent;
	}



	public TreeModel(String id, String name, String code, String parent) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.parent = parent;
	}
	
}
