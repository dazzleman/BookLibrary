package ru.ic218.booklibrary.data.db

import io.reactivex.Flowable
import ru.ic218.booklibrary.model.db.BookEntity
import ru.ic218.booklibrary.model.db.CategoryEntity

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

interface DbProvider {

    fun saveCategories(categories: ArrayList<CategoryEntity>)
    fun saveBooks(books: ArrayList<BookEntity>)
    fun getBooksFromCategory(idCategory: Int): Flowable<List<BookEntity>>
    fun getCategories(): Flowable<List<CategoryEntity>>
}