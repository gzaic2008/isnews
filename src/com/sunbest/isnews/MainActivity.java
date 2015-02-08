package com.sunbest.isnews;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.sunbest.isnews.MyListView.OnRefreshListener;

public class MainActivity extends Activity {

	private Button btnopen;
	private Button btnalert;

	private Button btnadd;

	private Dialog pd;

	// private ListView userlist;

	// private ReflashListview userlist;

	// private CustomListView userlist;

	private MyListView userlist;

	private LvAdapter adapter;

	private List<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/*
		 * setContentView(R.layout.activity_main);
		 * 
		 * btnopen = (Button) findViewById(R.id.btnopen);
		 * 
		 * btnalert = (Button) findViewById(R.id.btnalert);
		 * 
		 * btnopen.setOnClickListener(lister);
		 * btnalert.setOnClickListener(lister);
		 * 
		 * btnadd = (Button) findViewById(R.id.btnAdd);
		 * 
		 * pd = new AlertDialog.Builder(this).setTitle("title")
		 * .setMessage("processing...").create();
		 * 
		 * // pd.show();
		 */

		// listview demo
		setContentView(R.layout.userlist);
		// userlist = (ListView) findViewById(R.id.listView1);

		// 使用自定义的listview
		// userlist = (ReflashListview) findViewById(R.id.listView1);

		// userlist = (CustomListView) findViewById(R.id.listView1);

		userlist = (MyListView) findViewById(R.id.listView1);

		String[] users = new String[] { "user1", "user2", "张山", "历史", "斯福",
				"历始发牢骚式", "骚连接方式", "历始发牢骚式44", "骚连接方式tt", "历始发牢骚式ggg",
				"骚连接方式yyy" };
		// user android item
		ArrayAdapter<String> arrdp = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, users);

		list = new ArrayList<String>();
		list.add("loonggg");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");
		list.add("我们都是开发者");

		adapter = new LvAdapter(list, this);
		userlist.setAdapter(adapter);

		userlist.setonRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				new AsyncTask<Void, Void, Void>() {
					protected Void doInBackground(Void... params) {
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							e.printStackTrace();
						}
						list.add("刷新后添加的内容");
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						adapter.notifyDataSetChanged();
						userlist.onRefreshComplete();
					}
				}.execute(null, null, null);
			}
		});

		// userlist.setAdapter(arrdp);

		// userlist.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> parent, View view,
		// int position, long id) {
		// // TODO Auto-generated method stub
		// TextView itemv = (TextView) view;
		//
		//
		// Toast.makeText(MainActivity.this, itemv.getText(),
		// Toast.LENGTH_SHORT).show();
		//
		// }
		//
		//
		// });

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

				// pd.show();

				// getXml();

				// 3. call the secord activity here

				Intent intent = new Intent();
				// intent.setClass(MainActivity.this, UserFormActivity.class);
				// intent.setClass(MainActivity.this, PoupWindowActivity.class);

				// intent.setClass(MainActivity.this, PoupWindowActivity.class);
				// Intent.ACTION_AIRPLANE_MODE_CHANGED;
				// intent.putExtra("parentid", "guid");

				intent.setClass(MainActivity.this, MyWebview.class);

				startActivity(intent);

				// //startActivityForResult(intent, REQUEST_CODE);
				// intent.setAction(Intent.ACTION_CALL);
				// startActivity(intent);

			}

		}

	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// TODO Auto-generated method stub

		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_CODE)

		{

			if (resultCode == 1)

			{

				Bundle bundle = data.getExtras();

				String str = bundle.getString("back");

				Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG)
						.show();

			}

		}

	}

	private static final int REQUEST_CODE = 1;

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
