package com.example.treeview;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class TreePagerAdapter extends PagerAdapter{

	List<ListView> list;
	ViewPager mPager;
	
	public TreePagerAdapter(ViewPager viewPager, List<ListView> list) {
		this.list = list;
		mPager = viewPager;
	}
	
	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager)container).removeView(list.get(position));
	}
	
	@Override
	public int getCount() {
		return list.size();
	}
	
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v = list.get(position);
		v.setTag("pager_" + position);
		((ViewPager)container).addView(v, 0);
		return list.get(position);
	}
	
	@Override
	public Object instantiateItem(View container, int position) {
		View v = list.get(position);
		v.setTag("pager_" + position);
		((ViewPager)container).addView(v, 0);
		return list.get(position);
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}
	
	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}
	
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}
}
