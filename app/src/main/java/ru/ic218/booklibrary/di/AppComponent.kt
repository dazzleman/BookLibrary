package ru.ic218.booklibrary.di

import dagger.Component
import ru.ic218.booklibrary.ui.main.MainActivity
import javax.inject.Singleton

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class,DataModule::class))
interface AppComponent {

    fun inject(activity: MainActivity)
}