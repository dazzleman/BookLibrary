package ru.ic218.booklibrary.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.ic218.booklibrary.data.db.dao.BookDao
import ru.ic218.booklibrary.data.db.dao.CategoryDao
import ru.ic218.booklibrary.model.db.BookEntity
import ru.ic218.booklibrary.model.db.CategoryEntity

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

@Database(entities = arrayOf(
        CategoryEntity::class,
        BookEntity::class), version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun bookDao(): BookDao
}