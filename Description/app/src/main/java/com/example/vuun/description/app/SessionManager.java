package com.example.vuun.description.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by gowthamandroiddeveloperlogin Chandrasekar on 04-09-2015.
 */
public class SessionManager {
    // Shared preferences file name
    private static final String PREF_NAME = "AndroidSources";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";

    // Shared Preferences
    SharedPreferences pref;
    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
    }

    public void setUsername(String username) {
        editor.putString(KEY_USERNAME, username);
        // commit changes
        editor.commit();
    }
    public void setEmail(String email) {
        editor.putString(KEY_EMAIL, email);
        // commit changes
        editor.commit();
    }
    public String getUsername() {
        return pref.getString(KEY_USERNAME,"");
    }
    public String getEmail() {
        return pref.getString(KEY_EMAIL,"");
    }


    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}
