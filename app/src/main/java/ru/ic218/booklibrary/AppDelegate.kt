package ru.ic218.booklibrary

import android.app.Application
import android.content.Context
import ru.ic218.booklibrary.di.AppComponent
import ru.ic218.booklibrary.di.AppModule
import ru.ic218.booklibrary.di.DaggerAppComponent
import ru.ic218.booklibrary.di.DataModule

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

class AppDelegate : Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = prepareAppComponent()
    }

    private fun prepareAppComponent() =
            DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .dataModule(DataModule())
                    .build()

    fun getComponent(): AppComponent {
        return appComponent
    }

    companion object {
        fun getInstance(context: Context) = context.applicationContext as AppDelegate
    }
}