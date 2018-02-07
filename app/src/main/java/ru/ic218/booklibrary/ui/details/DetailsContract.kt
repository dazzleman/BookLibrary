package ru.ic218.booklibrary.ui.details

import ru.ic218.booklibrary.model.db.BookEntity
import ru.ic218.booklibrary.ui.common.BaseContract
import ru.ic218.booklibrary.ui.details.adapter.DetailsAdapter

/**
 * @author Nikolay Vlaskin on 06.02.2018.
 */

interface DetailsContract {

    interface View : BaseContract.View {
        fun setAdapter(adapter: DetailsAdapter)
        fun showDialogAddBook(idBook: Int, idCategory: Int)
        fun showDialogEditBook(book: BookEntity)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun init(idCategory: Int)
        fun createBook()
    }
}