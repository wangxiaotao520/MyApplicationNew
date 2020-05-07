package com.wangxiaotao.myapplicationnew.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 禁止滑动的listview
 */
public class ListViewNoScroll extends ListView{

	/**
	 * @param
	 */
	public ListViewNoScroll(Context context) {
		super(context); 
	}
	public ListViewNoScroll(Context context, AttributeSet attrs) {
		super(context, attrs); 
	}
	public ListViewNoScroll(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle); 
		 
	} 
	@Override 
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(  
				Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  

		super.onMeasure(widthMeasureSpec, expandSpec); 
	} 
}
