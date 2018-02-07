package ru.ic218.booklibrary.ui.details.adapter

import android.support.v7.util.DiffUtil
import ru.ic218.booklibrary.model.db.BookEntity

/**
 * @author Nikolay Vlaskin on 07.02.2018.
 */

class BooksDiffCallback(var oldList: List<BookEntity>, var newList: List<BookEntity>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldBook: BookEntity = oldList[oldItemPosition]
        val newBook: BookEntity = newList[newItemPosition]

        if (oldBook.idCategory == newBook.idCategory
                && oldBook.name == newBook.name
                && oldBook.description == newBook.description) {
            return true
        }
        return false
    }
}