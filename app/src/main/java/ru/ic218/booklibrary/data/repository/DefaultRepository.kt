package ru.ic218.booklibrary.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import ru.ic218.booklibrary.model.db.BookEntity
import ru.ic218.booklibrary.model.db.CategoryEntity

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

interface DefaultRepository {

    var isFirstStartupApplication: Boolean

    fun createDataAndSaveDB(): Single<Unit>

    fun getBooksFromDBByCategory(idCategory: Int): Flowable<List<BookEntity>>

    fun getBookById(idBook: Int): Single<BookEntity>

    fun updateBook(book: BookEntity)

    fun getCategories(): Flowable<List<CategoryEntity>>

    fun addBook(book: BookEntity)
}