package com.taeyeon.core;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.*;

public final class Settings {

	private static SharedPreferencesManager sharedPreferencesManager;

	private static final String SETTINGS_KEY = "SETTINGS";

	public static final int SYSTEM_MODE = 0;
	public static final int LIGHT_MODE = 1;
	public static final int DARK_MODE = 2;

	private static final SettingsData INITIAL_SETTINGS_DATA =
		new SettingsData(
			false, // FullScreenMode
			SYSTEM_MODE // DarkMode
		);

	public static SettingsData getInitialSettingsData() {
		return INITIAL_SETTINGS_DATA;
	}

	private static SettingsData settingsData;

	public static SettingsData getSettingsData() {
		return settingsData;
	}

	public static class SettingsData implements Cloneable {
		private boolean FullScreenMode;
		private int DarkMode;

		public boolean getFullScreenMode() {
			return FullScreenMode;
		}

		public void setFullScreenMode(boolean FullScreenMode) {
			this.FullScreenMode = FullScreenMode;
			saveSettings();
		}

		public int getDarkMode() {
			return DarkMode;
		}

		public void setDarkMode(int DarkMode) {
			this.DarkMode = DarkMode;
			saveSettings();
		}

		public SettingsData(
			boolean FullScreenMode,
			int DarkMode
		) {
			this.FullScreenMode = FullScreenMode;
			if (DarkMode == SYSTEM_MODE || DarkMode == LIGHT_MODE || DarkMode == DARK_MODE) this.DarkMode = DarkMode;
			else if (INITIAL_SETTINGS_DATA.DarkMode == SYSTEM_MODE || INITIAL_SETTINGS_DATA.DarkMode == LIGHT_MODE || INITIAL_SETTINGS_DATA.DarkMode == DARK_MODE) this.DarkMode = INITIAL_SETTINGS_DATA.DarkMode;
			else this.DarkMode = SYSTEM_MODE;
		}

		public SettingsData() {
			this(
				INITIAL_SETTINGS_DATA.getFullScreenMode(),
				INITIAL_SETTINGS_DATA.getDarkMode()
			);
		}

		public SettingsData clone() throws CloneNotSupportedException {
			return (SettingsData) super.clone();
		}

	}

	public static final void loadSettings() {
		try {
			//settingsData = sharedPreferencesManager.<SettingsData>getObject(SETTINGS_KEY, INITIAL_SETTINGS_DATA.clone());
		} catch (Exception exception) {
			Error.log(exception);
		}
	}

	public static final void saveSettings() {
		if (Core.isSetUp()) sharedPreferencesManager.putObject(SETTINGS_KEY, settingsData);
		applySettings();
	}

	public static final void applySettings() {
		if (Core.isSetUp()) applyFullScreenMode();
	}

	public static final void applyFullScreenMode() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			Window window = Core.getActivity().getWindow();
			WindowInsetsController contoller = window.getInsetsController();
			if (settingsData.FullScreenMode) {
				window.setDecorFitsSystemWindows(false);
				contoller.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
				contoller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
			} else {
				window.setDecorFitsSystemWindows(true);
			}
		} else {
			int flag;
			if (settingsData.FullScreenMode) {
				flag =
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE | 
					View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
					View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
					View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
			} else {
				flag = 
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
			}
			Core.getActivity().getWindow().getDecorView().setSystemUiVisibility(flag);
			Core.getActivity().getWindow().getDecorView().requestLayout();
		}
	}

	public static final void initializeSettings() {
		try {
			settingsData = INITIAL_SETTINGS_DATA.clone();
			saveSettings();
		} catch (CloneNotSupportedException exception) {
			Error.log(exception);
		}
	}

	public static final void initialize() {
		if (!Core.isSetUp()) {
			sharedPreferencesManager = new SharedPreferencesManager(SETTINGS_KEY);
			loadSettings();
			//applySettings();
		}
	}

}

