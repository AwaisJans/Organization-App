package com.jans.organizations.tree.view.app.utilOrganization

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager private constructor() {
    fun putString(key: String?, value: String?) {
        editor!!.putString(key, value)
        editor!!.apply()
    }

    fun getString(key: String?, defaultValue: String?): String? {
        return sharedPreferences!!.getString(key, defaultValue)
    }

    fun putBoolean(key: String?, value: Boolean) {
        editor!!.putBoolean(key, value)
        editor!!.apply()
    }

    fun toggleBooleanValue(KEY_IS_RV: String?) {
        val isRV = sharedPreferences!!.getBoolean(KEY_IS_RV, false)
        val editor = sharedPreferences!!.edit()
        editor.putBoolean(KEY_IS_RV, !isRV)
        editor.apply()
    }

    fun getBoolean(key: String?): Boolean {
        return sharedPreferences!!.getBoolean(key, false)
    }

    // Similarly, add methods for other data types such as int, boolean, etc.
    fun removeKey(key: String?) {
        editor!!.remove(key)
        editor!!.apply()
    }

    fun clear() {
        editor!!.clear()
        editor!!.apply()
    }

    companion object {
        private const val PREF_NAME = "MyPreferences"
        private var sharedPreferences: SharedPreferences? = null
        private var editor: SharedPreferences.Editor? = null
        private var instance: SharedPreferencesManager? = null
        @Synchronized
        fun getInstance(context: Context): SharedPreferencesManager? {
            if (instance == null) {
                instance = SharedPreferencesManager()
                sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                editor = sharedPreferences!!.edit()
            }
            return instance
        }
    }
}