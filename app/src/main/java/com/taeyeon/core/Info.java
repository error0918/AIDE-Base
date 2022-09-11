package com.taeyeon.core;

import android.content.pm.*;
import android.graphics.drawable.*;
import com.taeyeon.aide_base.*;

public final class Info {
	private static String ApplicationName;
	private static String ApplicationExplanation;
	private static String Package;
	private static String VersionName;
	private static int VersionCode;
	private static String Maker;
	
	public static final String getApplicationName() {
		return ApplicationName;
	}

	public static final String getApplicationExplanation() {
		return ApplicationExplanation;
	}

	public static final String getPackage() {
		return Package;
	}

	public static final String getVersionName() {
		return VersionName;
	}

	public static final int getVersionCode() {
		return VersionCode;
	}

	public static final String getMaker() {
		return Maker;
	}
	
	public static final void initialize() {
		if (!Core.isSetUp()) {
			try {
				PackageManager packageManager = Core.getContext().getPackageManager();
				PackageInfo packageInfo = packageManager.getPackageInfo(Core.getContext().getPackageName(), 0);
				ApplicationInfo applicationInfo = Core.getContext().getApplicationInfo();
				
				ApplicationName = packageManager.getApplicationLabel(applicationInfo).toString();
				ApplicationExplanation = Core.getContext().getResources().getString(R.string.app_explanation);
				Package = Core.getContext().getPackageName();
				VersionName = packageInfo.versionName;
				VersionCode = packageInfo.versionCode;
				Maker = Core.getContext().getResources().getString(R.string.maker);
			} catch (Exception exception) {
				Error.log(exception);
			}
		}
	}
	
}
