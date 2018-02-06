package ru.ic218.booklibrary.model.db

/**
 * @author Nikolay Vlaskin on 06.02.2018.
 */

data class Books(val category: Int, val text: String, var books: List<BookEntity>?)