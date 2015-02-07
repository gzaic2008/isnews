/**
 * 
 */
package com.sunbest.isnews;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.sunbest.isnews.R;

/**
 * @author senchokong
 *
 */
public class MyWebview extends Activity {

	private WebView webview = null;
	static final String MINE_TYPE = "text/html";

	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.webview);

		webview = (WebView) findViewById(R.id.testwebview);
//		webview.loadDataWithBaseURL(
//				null,
//				"<a href='http://blog.csdn.net/xys289187120'>欢迎访问雨松MOMO的博客</a>",
//				MINE_TYPE, "UTF-8", null);
//		
		
		//webview.getSettings().setJavaScriptEnabled(false);

		webview.getSettings().setSupportZoom(false);

		webview.getSettings().setBuiltInZoomControls(false);

		//mWebView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);

		webview.getSettings().setDefaultFontSize(18);
		webview.loadUrl("http://blog.csdn.net/leftfist");
		
		super.onCreate(savedInstanceState);
		
		
		

	}

}
