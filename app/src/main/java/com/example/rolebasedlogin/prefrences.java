package com.example.rolebasedlogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class prefrences {
    public static final String DATA_LOGIN = "Status Login",
            DATA_AS="as";
    private static SharedPreferences sharedPreferences (Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);

    }
    public static void setDataAs(Context context, String data){
        SharedPreferences.Editor editor = sharedPreferences(context).edit();
        editor.putLong(DATA_AS, Long.parseLong(data));
        editor.apply();

    }
    public  static  String setDataAs(Context context){
        return sharedPreferences(context).getString(DATA_AS,"");

    }
    public static void setDataLogin(Context context, Boolean status){
        SharedPreferences.Editor editor= sharedPreferences(context).edit();
        editor.putBoolean(DATA_AS,status);
        editor.apply();
    }
    public static boolean setDataLogin(Context context){
        return sharedPreferences(context).getBoolean(DATA_AS,false);
    }
    public static void setClearData(Context context){
        SharedPreferences.Editor editor = sharedPreferences(context).edit();
        editor.remove(DATA_AS);
        editor.remove(DATA_LOGIN);
        editor.apply();
    }
}
