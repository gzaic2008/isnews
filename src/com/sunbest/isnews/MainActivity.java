package com.sunbest.isnews;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.sunbest.isnews.base.PropertiesUtil;

public class MainActivity extends Activity {

	private Button btnopen;
	private Button btnalert;

	private Dialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnopen = (Button) findViewById(R.id.btnopen);

		btnalert = (Button) findViewById(R.id.btnalert);

		btnopen.setOnClickListener(lister);
		btnalert.setOnClickListener(lister);

		pd = new AlertDialog.Builder(this).setTitle("title")
				.setMessage("processing...").create();

		// pd.show();

	}

	private OnClickListener lister = new OnClickListener() {

		public void onClick(View v) {
			// TODO Auto-generated method stub

			Log.i("demo", v.getId() + "");

			if (v.getId() == R.id.btnalert) {
				Toast toast = Toast.makeText(getApplicationContext(),
						"test toast text", Toast.LENGTH_LONG);

				// 1. show text on center screen
				// toast.setGravity(Gravity.CENTER, 0, 0);

				// 2. set image as backgroud
				//
				// ImageView imgview = new ImageView(getApplicationContext());
				// imgview.setImageResource(R.drawable.y9);
				// LinearLayout toastview = (LinearLayout) toast.getView();
				// toastview.setOrientation(LinearLayout.HORIZONTAL);
				// toastview.addView(imgview,0);

				// toast.show();

				pd.show();
				
				
				getXml();

			}

		}

	};

	// private EditText txCity;
	// private Button btnSearch;
	// private Dialog progressDialog;
	//
	// private String cityName;//城市名称
	// private String cityCode;//城市编码
	// private int day = 0;// 当天
	// @Override
	// public void onCreate(Bundle savedInstanceState) {
	//
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.main);
	// txCity = (EditText) findViewById(R.id.txCity);
	// btnSearch = (Button) findViewById(R.id.btnSearch);
	// progressDialog = new AlertDialog.Builder(this).setTitle("数据读取中")
	// .setMessage("正在读取数据").create();
	//
	// final Handler handler = new Handler() {
	// @Override
	// public void handleMessage(Message msg) {
	// // super.handleMessage(msg);
	// if (msg.what == 1) {
	// layoutTable();
	// progressDialog.hide();
	// }
	// }
	// };
	// btnSearch.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// progressDialog.show();
	// // new Thread(runnable).start();
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// Message message = new Message();
	// /*
	// * message.setTarget(handler); message.sendToTarget();
	// */
	// cityName = txCity.getText().toString();
	// // Toast.makeText(WeatherActivity.this, cityName,
	// // 1).show();
	// checkCityName();//非空判断
	// //converCityCode();//城市编码转换
	// weather = getXml();//获取城市对象实体类
	// Bundle Bundle = new Bundle();
	// Bundle.putBoolean("1", false);
	// message.setData(Bundle);
	// message.what = 1;
	// handler.sendMessageDelayed(message, 200);
	// }
	// }).start();
	// }
	// });
	//
	//
	// }
	//
	//
	//
	// private void layoutTable() {
	// TableLayout table = (TableLayout) findViewById(R.id.table);
	// table.removeAllViews();
	// TableRow row = new TableRow(this);
	// row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
	// LayoutParams.WRAP_CONTENT));
	// row.setGravity(Gravity.CENTER_VERTICAL);
	//
	// TextView day = new TextView(this);
	// day.setText(weather.getSavedate_weather());
	// day.setGravity(Gravity.CENTER_HORIZONTAL);
	// row.addView(day);
	// TextView temp = new TextView(this);
	// temp.setText(weather.getTemperature1() + "℃-"
	// + weather.getTemperature2() + "℃");
	// temp.setGravity(Gravity.CENTER_HORIZONTAL);
	// row.addView(temp);
	// TextView condition = new TextView(this);
	// condition.setText(weather.getStatus1());
	// condition.setGravity(Gravity.CENTER_HORIZONTAL);
	// row.addView(condition);
	// table.addView(row);
	// }
	//
	//
	// private void checkCityName() {
	// if (null == cityName || "".equals(cityName)) {
	// new AlertDialog.Builder(this).setTitle("输入不正确")
	// .setMessage("城市名不能为空!").setPositiveButton("确定", null)
	// .show();
	//
	// return;
	// }
	//
	// }
	private void getXml() {
		URL url = null;
		String weatherUrl = "http://php.weather.sina.com.cn/xml.php?password=DJOYnieT8234jlsK&day=0";

		weatherUrl += "&city=广州";

		// Weather weather = null;
		try {
			url = new URL(weatherUrl);

			XmlPullParser parser = Xml.newPullParser();
			/**
			 * 读取本地数据 getResources().openRawResource(R.raw.weather);
			 * parser.setInput(getResources().openRawResource(R.raw.weather),
			 * "UTF-8");
			 */

			/*
			 * Reader reader = new BufferedReader(new InputStreamReader(
			 * url.openStream(), "UTF-8")); parser.setInput(reader);
			 */
			parser.setInput(url.openStream(), "UTF-8");

			int event = parser.getEventType();

			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_DOCUMENT:

					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("Weather")) {
						// weather = new Weather();
					}

					Log.i("demo", parser.nextText());

					// if (null != weather) {
					// if (parser.getName().equals("status1")) {
					// weather.setStatus1(parser.nextText());
					// } else if (parser.getName().equals("temperature1")) {
					// weather.setTemperature1(parser.nextText());
					// } else if (parser.getName().equals("temperature2")) {
					// weather.setTemperature2(parser.nextText());
					// } else if (parser.getName().equals("savedate_weather")) {
					// weather.setSavedate_weather(parser.nextText());
					// }
					// }

					break;
				case XmlPullParser.END_TAG:

					break;

				default:
					break;
				}
				event = parser.next();

			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return weather;

	}
}
