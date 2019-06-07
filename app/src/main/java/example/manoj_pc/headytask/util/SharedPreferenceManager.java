package example.manoj_pc.headytask.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager
{
    private static final String PREF_NAME = "HEADY_PREFERENCES";
    private static final int MODE = Context.MODE_PRIVATE;

    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();
    }

    public static String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public static void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();
    }

    public static int readInteger(Context context,String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }
}
