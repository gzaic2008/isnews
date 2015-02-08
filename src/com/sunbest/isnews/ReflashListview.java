package com.sunbest.isnews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public class ReflashListview extends ListView implements OnScrollListener {

	View header;

	int firstVisibleItem;

	boolean isMark = false; // 标志状态
	int startY = 0; // 开始位置

	int state = 0; // 操作状态

	final int NONE = 0; // 正常
	final int PULL = 1; // 下拉
	final int RELEASE = 2; // 释放
	final int REFLASH = 3; // 刷新

	int headerHeight; // header layout 's height

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

		this.measureView(header);

		// 隐藏 header layout
		int iheight = header.getMeasuredHeight(); // layout's height
		this.setPadding(-iheight);

		// header.setTop(iheight);

		this.setOnScrollListener(this);

		addHeaderView(header);

	}

	// 通知父布局本控件所占用的宽高
	private void measureView(View view) {

		ViewGroup.LayoutParams p = view.getLayoutParams();

		if (p == null) {
			// default width , height
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		} else {

		}

		int width = ViewGroup.getChildMeasureSpec(0, 0, p.width);

		int height;

		int temp = p.height;

		if (temp > 0) {

			height = MeasureSpec.makeMeasureSpec(temp, MeasureSpec.EXACTLY);

		} else {
			height = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);

		}

		view.measure(width, height);

		headerHeight = height;

	}

	private void setPadding(int pad) {

		header.setPadding(header.getPaddingLeft(), pad,
				header.getPaddingRight(), header.getPaddingBottom());
		header.invalidate();

	}

	private int scrollState;

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

		this.scrollState = scrollState;

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

		this.firstVisibleItem = firstVisibleItem;

	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 恩下
			if (this.firstVisibleItem == 0) {
				isMark = true;
				startY = (int) ev.getY();

			}

			break;

		case MotionEvent.ACTION_UP:
			
			//release the touch

			if(state == RELEASE){
				state = REFLASH;
				//loading data here
			}else if(state == PULL){
				state = NONE;
				// not loading data here
				isMark = false;
			}
			
			break;

		case MotionEvent.ACTION_MOVE:
			// 移动
			moveEvent(ev);

			break;

		case MotionEvent.ACTION_SCROLL:
			break;

		}

		return super.onTouchEvent(ev);
	}

	private void moveEvent(MotionEvent ev) {
		this.setPadding((int) ev.getY() - startY);

		if (!isMark) {
			// isMark = false;
			return;
		}

		int space = (int) ev.getY() - startY;
		
		int padding = space - this.headerHeight;
		
		

		switch (state) {
		case NONE:
			if (space > 0) {

				state = PULL;
			}

			break;

		case PULL:
			this.setPadding(padding);
			if (space > this.headerHeight + 30
					&& scrollState == SCROLL_STATE_TOUCH_SCROLL) {
				state = RELEASE;

			}

			break;
		case RELEASE:
			this.setPadding(padding);
			if (space < this.headerHeight + 30) {
				state = PULL;

			} else if (space <= 0) {
				state = NONE;
				isMark = false;

			}
			break;
		case REFLASH:

			break;

		default:
			break;
		}

	}
}
