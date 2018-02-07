package ru.ic218.booklibrary.ui.details

import ru.ic218.booklibrary.data.repository.DefaultRepository
import ru.ic218.booklibrary.ui.common.BasePresenter
import ru.ic218.booklibrary.ui.details.adapter.DetailsAdapter
import ru.ic218.booklibrary.utils.applySchedulers

/**
 * @author Nikolay Vlaskin on 06.02.2018.
 */

class DetailsPresenter(private val defaultRepository: DefaultRepository)
    : BasePresenter<DetailsContract.View>(), DetailsContract.Presenter {

    private var adapter = DetailsAdapter(onItemClick = { view?.showDialogEditBook(it) })
    private var idCategory = 0

    override fun init(idCategory: Int) {
        this.idCategory = idCategory
        defaultRepository.getBooksFromDBByCategory(idCategory)
                .applySchedulers()
                .subscribe({
                    adapter.addBooks(it)
                    view?.setAdapter(adapter)
                })
                .addToDisposables()
    }

    override fun createBook() {
        view?.showDialogAddBook(System.currentTimeMillis().toInt(), idCategory)
    }
}