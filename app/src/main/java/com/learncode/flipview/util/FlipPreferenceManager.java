package com.learncode.flipview.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Preeti on 25/07/16.
 */
public class FlipPreferenceManager {

    private static FlipPreferenceManager sInstance;
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private static final String USER_PREF_FILE_NAME = "FlipPreferenceManager";

    public static FlipPreferenceManager instance(Context context) {
        if (sInstance == null) {
            sInstance = new FlipPreferenceManager(context);
        }
        return sInstance;
    }

    private FlipPreferenceManager(Context ctx) {
        mPreferences = ctx.getSharedPreferences(USER_PREF_FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public String getLastSearch()
    {
        return mPreferences.getString(Key.LAST_SEARCH, null);
    }
    public void setLastSearch(String tag)
    {
        mEditor.putString(Key.LAST_SEARCH, tag).commit();
    }

    interface Key{
        public static final String LAST_SEARCH = "lsrch";
    }


}
