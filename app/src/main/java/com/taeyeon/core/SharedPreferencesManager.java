package com.taeyeon.core;

import android.content.*;
import com.google.gson.*;
import com.google.gson.reflect.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.*;
import java.util.*;
import androidx.core.util.*;

public final class SharedPreferencesManager {

	public static final String DEFAULT_NAME = "SHARED_PREFERENCES";
	public static final int DEFAULT_MODE = 0;

	public static final boolean DEFAULT_BOOLEAN = false;
	public static final int DEFAULT_INT = 0;
	public static final float DEFAULT_FLOAT = 0.0F;
	public static final long DEFAULT_LONG = 0L;
	public static final String DEFAULT_STRING = "";


	private static final ArrayList<SharedPreferencesManager> sharedPreferencesManagers = new ArrayList<SharedPreferencesManager>();


	public static final ArrayList<SharedPreferencesManager> getSharedPreferencesManagers() {
		return SharedPreferencesManager.sharedPreferencesManagers;
	}


	public static final void clearAllSharedPreferencesManagers() {
		for (SharedPreferencesManager sharedPreferencesManager : sharedPreferencesManagers) {
			sharedPreferencesManager.clear();
		}
	}


	public static final class Public {

		private static SharedPreferencesManager sharedPreferencesManager;


		public static final String NAME = "PUBLIC_SHAREEPREFERENCES_MANAGER";
		public static final int MODE = Context.MODE_PRIVATE; 

		private static boolean defaultBoolean = DEFAULT_BOOLEAN;
		private static int defaultInt = DEFAULT_INT;
		private static float defaultFloat = DEFAULT_FLOAT;
		private static long defaultLong = DEFAULT_LONG;
		private static String defaultString = DEFAULT_STRING;



		public static final void initialize() {
			if (!Core.isSetUp()) {
				sharedPreferencesManager = new SharedPreferencesManager(NAME, MODE);
			}
		}


		public static final SharedPreferencesManager getPublicSharedPreferencesMamager() {
			return sharedPreferencesManager;
		}

		public static final boolean getDefaultBoolean() {
			return defaultBoolean;
		}

		public static final void setDefaultBoolean(boolean defaultBoolean) {
			Public.defaultBoolean = defaultBoolean;
		}

		public static final int getDefaultInt() {
			return defaultInt;
		}

		public static final void setDefaultInt(int defaultInt) {
			Public.defaultInt = defaultInt;
		}

		public static final float getDefaultFloat() {
			return defaultFloat;
		}

		public static final void setDefaultFloat(float defaultFloat) {
			Public.defaultFloat = defaultFloat;
		}

		public static final long getDefaultLong() {
			return defaultLong;
		}

		public static final void setDefaultLong(long defaultLong) {
			Public.defaultLong = defaultLong;
		}

		public static final String getDefaultString() {
			return defaultString;
		}

		public static final void setDefaultString(String defaultString) {
			Public.defaultString = defaultString;
		}


		public static final Map<String, ?> getAll() {
			return sharedPreferencesManager.getAll();
		}

        public static final void clear() {
            sharedPreferencesManager.clear();
        }

        public static final boolean contains(String key) {
            return sharedPreferencesManager.contains(key);
        }

        public static final void remove(String key) {
            sharedPreferencesManager.remove(key);
        }


        public static final boolean getBoolean(String key, boolean defaultBoolean) {
            return sharedPreferencesManager.getBoolean(key, defaultBoolean);
        }

        public static final boolean getBoolean(String key) {
            return getBoolean(key, defaultBoolean);
        }

        public static final int getInt(String key, int defaultInt) {
            return sharedPreferencesManager.getInt(key, defaultInt);
        }

        public static final int getInt(String key) {
            return getInt(key, defaultInt);
        }

        public static final float getFloat(String key, float defaultFloat) {
            return sharedPreferencesManager.getFloat(key, defaultFloat);
        }

        public static final float getFloat(String key) {
            return getFloat(key, defaultFloat);
        }

        public static final long getLong(String key, long defaultLong) {
            return sharedPreferencesManager.getLong(key, defaultLong);
        }

        public static final long getLong(String key) {
            return getLong(key, defaultLong);
        }

        public static final <E> ArrayList<E> getArrayList(String key, ArrayList<E> defaultArrayList) {
            return sharedPreferencesManager.getArrayList(key, defaultArrayList);
        }

        public static final <E> ArrayList<E> getArrayList(String key) {
            return getArrayList(key, new ArrayList<E>());
        }

		public static final <E> E getObject(String key, E defaultObject) {
			return sharedPreferencesManager.getObject(key, defaultObject);
		}


		public static final void put(Pair<String, ?> ...datas) {
			sharedPreferencesManager.put(datas);
		}

		public static final void put(String key, Object data) {
			sharedPreferencesManager.put(key, data);
		}

		public final void putBoolean(String key, boolean data) {
			sharedPreferencesManager.putBoolean(key, data);
		}

		public final void putInt(String key, int data) {
			sharedPreferencesManager.putInt(key, data);
		}

		public final void putFloat(String key, float data) {
			sharedPreferencesManager.putFloat(key, data);
		}

		public final void putLong(String key, long data) {
			sharedPreferencesManager.putLong(key, data);
		}

		public final void putString(String key, String data) {
			sharedPreferencesManager.putString(key, data);
		}

		public final void putArrayList(String key, ArrayList data) {
			sharedPreferencesManager.putArrayList(key, data);
		}

		public final void putObject(String key, Object data) {
			sharedPreferencesManager.putObject(key, data);
		}

    }


	private final String name;
	private final int mode;
	private final SharedPreferences sharedPreferences;


	private boolean defaultBoolean;
	private int defaultInt;
	private float defaultFloat;
	private long defaultLong;
	private String defaultString;


	public SharedPreferencesManager(
		String name, int mode,
		boolean defaultBoolean, int defaultInt, float defaultFloat, long defaultLong, String defaultString
	) {
		this.name = name;
		this.mode = mode == Context.MODE_PRIVATE || mode != Context.MODE_WORLD_READABLE && mode != Context.MODE_WORLD_WRITEABLE ? mode : DEFAULT_MODE;
		this.sharedPreferences = Core.getContext().getSharedPreferences(name, mode);

		this.defaultBoolean = defaultBoolean;
		this.defaultInt = defaultInt;
		this.defaultFloat = defaultFloat;
		this.defaultLong = defaultLong;
		this.defaultString = defaultString;

		if (!sharedPreferencesManagers.contains(this)) sharedPreferencesManagers.add(this);
	}

	public SharedPreferencesManager(
		String name, int mode
	) {
		this(
			name, mode,
			DEFAULT_BOOLEAN, DEFAULT_INT, DEFAULT_FLOAT, DEFAULT_LONG, DEFAULT_STRING
		);
	}

	public SharedPreferencesManager(
		String name,
		boolean defaultBoolean, int defaultInt, float defaultFloat, long defaultLong, String defaultString
	) {
		this(
			name, DEFAULT_MODE,
			defaultBoolean, defaultInt, defaultFloat, defaultLong, defaultString
		);
	}

	public SharedPreferencesManager(
		String name
	) {
		this(
			name, DEFAULT_MODE,
			DEFAULT_BOOLEAN, DEFAULT_INT, DEFAULT_FLOAT, DEFAULT_LONG, DEFAULT_STRING
		);
	}

	public SharedPreferencesManager(
		int mode,
		boolean defaultBoolean, int defaultInt, float defaultFloat, long defaultLong, String defaultString
	) {
		this(
			DEFAULT_NAME, mode,
			defaultBoolean, defaultInt, defaultFloat, defaultLong, defaultString
		);
	}

	public SharedPreferencesManager(
		int mode
	) {
		this(
			DEFAULT_NAME, mode,
			DEFAULT_BOOLEAN, DEFAULT_INT, DEFAULT_FLOAT, DEFAULT_LONG, DEFAULT_STRING
		);
	}

	public SharedPreferencesManager(
		boolean defaultBoolean, int defaultInt, float defaultFloat, long defaultLong, String defaultString
	) {
		this(
			DEFAULT_NAME, DEFAULT_MODE,
			defaultBoolean, defaultInt, defaultFloat, defaultLong, defaultString
		);
	}

	public SharedPreferencesManager() {
		this(
			DEFAULT_NAME, DEFAULT_MODE,
			DEFAULT_BOOLEAN, DEFAULT_INT, DEFAULT_FLOAT, DEFAULT_LONG, DEFAULT_STRING
		);
	}


	public final SharedPreferences getSharedPreferences() {
		return sharedPreferences;
	}

	public final boolean getDefaultBoolean() {
		return defaultBoolean;
	}

	public final void setDefaultBoolean(boolean defaultBoolean) {
		this.defaultBoolean = defaultBoolean;
	}

	public final int getDefaultInt() {
		return defaultInt;
	}

	public final void setDefaultInt(int defaultInt) {
		this.defaultInt = defaultInt;
	}

	public final float getDefaultFloat() {
		return defaultFloat;
	}

	public final void setDefaultFloat(float defaultFloat) {
		this.defaultFloat = defaultFloat;
	}

	public final long getDefaultLong() {
		return defaultLong;
	}

	public final void setDefaultLong(long defaultLong) {
		this.defaultLong = defaultLong;
	}

	public final String getDefaultString() {
		return defaultString;
	}

	public final void setDefaultString(String defaultString) {
		this.defaultString = defaultString;
	}


	public final Map<String, ?> getAll() {
		return sharedPreferences.getAll();
	}

	public final void clear() {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.clear();
		editor.apply();
	}

	public final boolean contains(String key) {
		return this.sharedPreferences.contains(key);
	}

	public final void remove(String key) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.remove(key);
		editor.apply();
	}


	public final boolean getBoolean(String key, boolean defaultBoolean) {
		return this.sharedPreferences.getBoolean(key, defaultBoolean);
	}

	public final boolean getBoolean(String key) {
		return getBoolean(key, defaultBoolean);
	}

	public final int getInt(String key, int defaultInt) {
		return this.sharedPreferences.getInt(key, defaultInt);
	}

	public final int getInt(String key) {
		return getInt(key, defaultInt);
	}

	public final float getFloat(String key, float defaultFloat) {
		return this.sharedPreferences.getFloat(key, defaultFloat);
	}

	public final float getFloat(String key) {
		return getFloat(key, defaultFloat);
	}

	public final long getLong(String key, long defaultLong) {
		return this.sharedPreferences.getLong(key, defaultLong);
	}

	public final long getLong(String key) {
		return getLong(key, defaultLong);
	}

	public final String getString(String key, String defaultString) {
		return sharedPreferences.getString(key, defaultString);
	}

	public final String getString(String key) {
		return getString(key, defaultString);
	}

	public final <E> ArrayList<E> getArrayList(String key, ArrayList<E> defaultArrayList) {
		try {
			ArrayList<E> arrayList = new Gson().fromJson(sharedPreferences.getString(key, null), new TypeToken<ArrayList<E>>(){}.getType());
			return arrayList;
		} catch (Exception exception) {
			Error.log(exception);
			return defaultArrayList;
		}
	}

	public final <E> ArrayList<E> getArrayList(String key) {
		return getArrayList(key, new ArrayList<E>());
	}

	public final <E> E getObject(String key, E defaultObject) {
		try {
			return (new Gson()).fromJson(sharedPreferences.getString(key, (new Gson()).toJson(defaultObject)), new TypeToken<E>(){}.getType());
		} catch (Exception exception) {
			Error.log(exception);
			return defaultObject;
		}
	}


	public final void put(Pair<String, ?> ...datas) {
		for (Pair<String, ?> data : datas) {
			put(data.first, data.second);
		}
	}

	public final void put(String key, Object data) {
		if (data instanceof boolean) {
			putBoolean(key, (Boolean) data);
		} else if (data instanceof int) {
			putInt(key, (int) data);
		} else if (data instanceof float) {
			putFloat(key, (float) data);
		} else if (data instanceof String) {
			putString(key, (String) data);
		} else {
			putObject(key, data);
		}
	}

	public final void putBoolean(String key, boolean data) {
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, data);
		editor.apply();
	}

	public final void putInt(String key, int data) {
		final SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putInt(key, data);
		editor.apply();
	}

	public final void putFloat(String key, float data) {
		final SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putFloat(key, data);
		editor.apply();
	}

	public final void putLong(String key, long data) {
		final SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putLong(key, data);
		editor.apply();
	}

	public final void putString(String key, String data) {
		final SharedPreferences.Editor editor = this.sharedPreferences.edit();
		editor.putString(key, data);
		editor.apply();
	}

	public final void putArrayList(String key, ArrayList data) {
		final SharedPreferences.Editor editor = this.sharedPreferences.edit();
		editor.putString(key, (new Gson()).toJson(data));
		editor.apply();
	}

	public final void putObject(String key, Object data) {
		final SharedPreferences.Editor editor = this.sharedPreferences.edit();
		editor.putString(key, (new Gson()).toJson(data));
		editor.apply();
	}

}
