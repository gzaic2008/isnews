package com.sunbest.isnews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

public class ReflashListview extends ListView {

	View header;

	public ReflashListview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initView(context);
	}

	public ReflashListview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initView(context);
	}

	public ReflashListview(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		initView(context);
	}

	@SuppressLint("NewApi")
	public ReflashListview(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		initView(context);
	}

	// load the listview 's heard layout
	private void initView(Context cont) {

		LayoutInflater inflater = LayoutInflater.from(cont);
		header = inflater.inflate(R.layout.layoutheader, null);
		addHeaderView(header);

	}
}
