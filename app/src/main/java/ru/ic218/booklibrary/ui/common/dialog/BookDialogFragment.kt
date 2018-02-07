package ru.ic218.booklibrary.ui.common.dialog


import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.Single
import kotlinx.android.synthetic.main.dialog_view.*
import ru.ic218.booklibrary.AppDelegate
import ru.ic218.booklibrary.R
import ru.ic218.booklibrary.data.repository.DefaultRepository
import ru.ic218.booklibrary.model.db.BookEntity
import ru.ic218.booklibrary.utils.applySchedulers
import javax.inject.Inject

/**
 * @author Nikolay Vlaskin on 06.02.2018.
 */

class BookDialogFragment : DialogFragment() {

    companion object {

        const val CATEGORY_ID = "category_id"
        const val BOOK_ID = "book_id"
        const val NEW_IS = "new_is"

        fun newInstance(idBook: Int, idCategory: Int, isNew: Boolean): BookDialogFragment {
            val args = Bundle().apply {
                putBoolean(NEW_IS, isNew)
                putInt(BOOK_ID, idBook)
                putInt(CATEGORY_ID, idCategory)
            }
            return BookDialogFragment().apply { arguments = args }
        }
    }

    @Inject
    lateinit var defaultRepository: DefaultRepository

    private var book: BookEntity? = null
    private var isNew: Boolean = false
    private var idCategory: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_view, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AppDelegate.getInstance(context).getComponent().inject(this)

        btnCancel.setOnClickListener({ dismissAllowingStateLoss() })

        val idBook = arguments.getInt(BOOK_ID)

        idCategory = arguments.getInt(CATEGORY_ID)
        btnYes.setOnClickListener({ onClick(idBook) })

        if (arguments.getBoolean(NEW_IS)) {
            btnYes.text = "Добавить"
            isNew = true
        } else {
            defaultRepository.getBookById(idBook)
                    .applySchedulers()
                    .subscribe({
                        book = it
                        textEditTitle.setText(it.name)
                        textEditDescription.setText(it.description)
                    }, {
                        println(it.message)
                    })
        }
    }

    private fun onClick(idBook: Int) {
        if (isNew) {
            val b = BookEntity(idBook, idCategory, textEditTitle.text.toString(), textEditDescription.text.toString())
            Single.just(true)
                    .map { defaultRepository.addBook(b) }
                    .applySchedulers()
                    .subscribe()
            dismissAllowingStateLoss()
        } else {
            val b = BookEntity(idBook, idCategory, textEditTitle.text.toString(), textEditDescription.text.toString())
            Single.just(true)
                    .map { defaultRepository.updateBook(b) }
                    .applySchedulers()
                    .subscribe()
            dismissAllowingStateLoss()
        }
    }
}