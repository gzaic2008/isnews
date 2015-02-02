package com.sunbest.isnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserFormActivity extends Activity {

	private EditText txtname;
	private Button btnok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.userform);

		txtname = (EditText) findViewById(R.id.tabMode);
		btnok = (Button) findViewById(R.id.btnok);
  
		// get the parent the activity object parameters
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		String str = bundle.getString("parentid");

		Log.i("demo", str);

		btnok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// return to parent data

				Intent intent = new Intent();
				intent.putExtra("back", "resultdata");
				setResult(1, intent);

				finish();

			}
		});

	}

	

}
