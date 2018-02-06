package ru.ic218.booklibrary.data.preferences

import android.content.SharedPreferences

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

class SharedPreferenceProvider(private val sp: SharedPreferences) : PreferenceProvider {

    companion object {
        private const val PREF_INSTALLATION_COMPLETED : String = "pref_installation_completed"
    }

    override var isFirstStartupApplication: Boolean
        get() = sp.getBoolean(PREF_INSTALLATION_COMPLETED, true)
        set(value) { sp.edit().putBoolean(PREF_INSTALLATION_COMPLETED, value).apply()  }
}