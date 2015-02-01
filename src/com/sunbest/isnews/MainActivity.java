package com.sunbest.isnews;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btnopen;
	private Button btnalert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnopen = (Button) findViewById(R.id.btnopen);

		btnalert = (Button) findViewById(R.id.btnalert);
		
		
		btnopen.setOnClickListener(lister);
		btnalert.setOnClickListener(lister);

	}

	private OnClickListener lister = new OnClickListener() {

		 
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Log.i("demo", v.getId() +"");
			
			
			if( v.getId() == R.id.btnalert){
				Toast toast = Toast.makeText(getApplicationContext(), "test toast text", Toast.LENGTH_LONG);

				//1. show text on center screen
				toast.setGravity(Gravity.CENTER, 0, 0);
				
				
				//2. set image as backgroud
				
				ImageView imgview = new ImageView(getApplicationContext());
				imgview.setImageResource(R.drawable.y9);
				LinearLayout toastview = (LinearLayout) toast.getView();
				toastview.setOrientation(LinearLayout.HORIZONTAL);
				toastview.addView(imgview,0);
				
				
				toast.show();
				
			}

		}

	};
}
