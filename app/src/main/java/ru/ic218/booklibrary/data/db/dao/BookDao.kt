package ru.ic218.booklibrary.data.db.dao

import android.arch.persistence.room.*
import io.reactivex.Flowable
import ru.ic218.booklibrary.model.db.BookEntity

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBook(book: BookEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg books: BookEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBook(book: BookEntity)

    @Query("select * from books where id = :id")
    fun findBookById(id: Int): BookEntity

    @Query("select * from books where idCategory = :id")
    fun getBooksFromCategory(id: Int): Flowable<List<BookEntity>>

    @Query("SELECT * FROM books")
    fun getBooks(): Flowable<List<BookEntity>>

    @Query("delete from books")
    fun clearBooks()
}