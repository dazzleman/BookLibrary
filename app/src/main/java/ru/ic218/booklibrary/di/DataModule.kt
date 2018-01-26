package ru.ic218.booklibrary.di

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.ic218.booklibrary.data.db.AppDatabase
import ru.ic218.booklibrary.data.db.DbProvider
import ru.ic218.booklibrary.data.db.RoomDbProvider
import ru.ic218.booklibrary.data.preferences.PreferenceProvider
import ru.ic218.booklibrary.data.preferences.SharedPreferenceProvider
import ru.ic218.booklibrary.data.repository.DefaultRepository
import ru.ic218.booklibrary.data.repository.DefaultRepositoryImpl
import javax.inject.Singleton

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "default").build()
    }

    @Singleton
    @Provides
    fun provideDbProvider(db: AppDatabase): DbProvider {
        return RoomDbProvider(db)
    }

    @Singleton
    @Provides
    fun providePreferenceProvider(sp: SharedPreferences): PreferenceProvider {
        return SharedPreferenceProvider(sp)
    }

    @Singleton
    @Provides
    fun provideDefaultRepository(
            dbProvider: DbProvider,
            preferenceProvider: PreferenceProvider): DefaultRepository {
        return DefaultRepositoryImpl(dbProvider, preferenceProvider)
    }
}