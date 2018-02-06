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

    private var adapter = DetailsAdapter(onItemClick = {})

    override fun init(idCategory: Int) {
        defaultRepository.getBooksFromDBByCategory(idCategory)
                .applySchedulers()
                .subscribe({
                    adapter.addBooks(ArrayList(it))
                    view?.setAdapter(adapter)
                })
                .addToDisposables()
    }
}