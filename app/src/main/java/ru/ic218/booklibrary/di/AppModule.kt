package ru.ic218.booklibrary.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

@Module
class AppModule(private val appContext: Context) {

    @Provides
    fun provideContext() = appContext

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}