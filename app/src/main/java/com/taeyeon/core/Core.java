package com.taeyeon.core;

import android.content.*;
import android.app.*;

public final class Core {
	private static boolean isSetUp = false;
	
	private static Context context;
	private static Activity activity;
	
	public static final boolean isSetUp() {
		return isSetUp;
	}
	
	public static final Context getContext() {
		return context;
	}
	
	public static final Activity getActivity() {
		return activity;
	}

	public static final void activityCreated(Activity activity) {
		Core.activity = activity;

		//Settings.applyFullScreenMode();
	}

	public static final void initialize(Context context) {
		if (!isSetUp) {
			Core.context = context;

			Info.initialize();
			Settings.initialize();
			SharedPreferencesManager.Public.initialize();

			isSetUp = true;
		}
	}
	
}
