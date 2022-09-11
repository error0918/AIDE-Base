package com.taeyeon.core;

import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.widget.*;
import com.taeyeon.aide_base.*;
import com.taeyeon.core.*;

import android.os.Process;

public final class Utils {
	private static int defaultToastLength;
	private static boolean defaultIsShowToast;
	
	public static final int getDefaultToastLength() {
		return defaultToastLength;
	}

	public static final void setDefaultToastLength(int defaultToastLength) {
		if (defaultToastLength == Toast.LENGTH_SHORT || defaultToastLength == Toast.LENGTH_LONG) {
			Utils.defaultToastLength = defaultToastLength;
		}
	}

	public static final boolean getDefaultIsShowToast() {
		return defaultIsShowToast;
	}

	public static final void setDefaultIsShowToast(boolean defaultIsShowToast) {
		Utils.defaultIsShowToast = defaultIsShowToast;
	}
	
	
	public static final void toast(String text, int length) {
		Toast.makeText(Core.getContext(), text, length == Toast.LENGTH_SHORT || length == Toast.LENGTH_LONG ? length : defaultToastLength).show();
	}
	
	public static final void toast(String text) {
		toast(text, defaultToastLength);
	}
	
	public static final void copy(String label, String text, boolean isShowToast, int toastLength) {
		ClipboardManager clipboardManager = Core.getContext().getSystemService(new Class<ClipboardManager>());
		ClipData clipData = ClipData.newPlainText(label, text);
		clipboardManager.setPrimaryClip(clipData);
		if (isShowToast) {
			if (label != Info.getApplicationName()) {
				toast(Core.getContext().getResources().getString(R.string.copy_toast_message, text));
			} else {
				toast(Core.getContext().getResources().getString(R.string.copy_toast_message_with_label, label, text));
			}
		}
	}
	
	public static final void copy(String label, String text, int toastLength) {
		copy(label, text, defaultIsShowToast, toastLength);
	}
	
	public static final void copy(String label, String text, boolean isShowToast) {
		copy(label, text, isShowToast, defaultToastLength);
	}
	
	public static final void copy(String label, String text) {
		copy(label, text, defaultIsShowToast, defaultToastLength);
	}
	
	public static final void copy(String text, boolean isShowToast, int toastLength) {
		copy(Info.getApplicationName(), text, isShowToast, toastLength);
	}
	
	public static final void copy(String text, int toastLength) {
		copy(Info.getApplicationName(), text, defaultIsShowToast, toastLength);
	}
	
	public static final void copy(String text, boolean isShowToast) {
		copy(Info.getApplicationName(), text, isShowToast, defaultToastLength);
	}

	public static final void copy(String text) {
		copy(Info.getApplicationName(), text, defaultIsShowToast, defaultToastLength);
	}
	
	public static final void vibrate(long milliseconds) {
		Vibrator vibrator = (Vibrator) Core.getContext().getSystemService(Context.VIBRATOR_SERVICE);
		vibrator.vibrate(milliseconds);
	}
	
	public static final void shutDownApp() {
		Process.killProcess(Process.myPid());
		System.exit(0);
	}
	
	public static final void restartApp() {
		PackageManager packageManager = Core.getContext().getPackageManager();
		Intent intent = packageManager.getLaunchIntentForPackage(Info.getPackage());
		ComponentName componentName = intent != null ? intent.getComponent() : null;
		Core.getActivity().startActivity(Intent.makeRestartActivityTask(componentName));
		System.exit(0);
	}
	
	public static final void initializeData() {
		SharedPreferencesManager.clearAllSharedPreferencesManagers();
		restartApp();
	}
	
}
