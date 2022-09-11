package com.taeyeon.aide_base;

import androidx.appcompat.app.*;
import android.os.*;
import com.taeyeon.core.*;
import android.content.*;

public class SplashActivity extends AppCompatActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Core.initialize(getApplicationContext());
		Core.activityCreated(this);

		// 앱 시작 전 작업 내용 작성 //
		
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onRestart() {
		super.onRestart();
		
		Core.activityCreated(this);
	}
}
