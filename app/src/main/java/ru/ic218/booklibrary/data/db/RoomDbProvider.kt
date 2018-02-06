package ru.ic218.booklibrary.data.db

import io.reactivex.Flowable
import ru.ic218.booklibrary.model.db.BookEntity
import ru.ic218.booklibrary.model.db.CategoryEntity

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

class RoomDbProvider(private var db: AppDatabase) : DbProvider {

    override fun saveCategories(categories: ArrayList<CategoryEntity>) {
        db.categoryDao().clearCategories()
        db.categoryDao().insertAllCategory(*categories.toTypedArray())
    }

    override fun saveBooks(books: ArrayList<BookEntity>) {
        db.bookDao().clearBooks()
        db.bookDao().insertAll(*books.toTypedArray())
    }

    override fun getBooksFromCategory(idCategory: Int): Flowable<List<BookEntity>> {
        return db.bookDao().getBooksFromCategory(idCategory)
    }

    override fun getCategories(): Flowable<List<CategoryEntity>> {
        return db.categoryDao().getCategories()
    }
}