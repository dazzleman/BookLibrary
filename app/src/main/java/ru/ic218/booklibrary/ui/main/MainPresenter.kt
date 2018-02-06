package ru.ic218.booklibrary.ui.main

import ru.ic218.booklibrary.data.repository.DefaultRepository
import ru.ic218.booklibrary.ui.common.BasePresenter
import ru.ic218.booklibrary.ui.main.adapter.MainAdapter
import ru.ic218.booklibrary.utils.applySchedulers

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

class MainPresenter(private val defaultRepository: DefaultRepository)
    : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private var adapter = MainAdapter(defaultRepository, onItemClick = {view?.runActivity(it)})

    override fun init() {

        if (defaultRepository.isFirstStartupApplication) {
            createDataAndLoadCategories()
        } else {
            getCategories()
        }
    }

    private fun createDataAndLoadCategories() {
        defaultRepository.createDataAndSaveDB()
                .doOnSuccess {
                    defaultRepository.getCategories()
                            .subscribe({ adapter.addCategories(ArrayList(it)) })
                }
                .applySchedulers()
                .subscribe({
                    view?.setAdapter(adapter)
                    defaultRepository.isFirstStartupApplication = false
                }, { println("${it.message}") })
                .addToDisposables()
    }

    private fun getCategories() {
        defaultRepository.getCategories()
                .applySchedulers()
                .subscribe({
                    adapter.addCategories(ArrayList(it))
                    view?.setAdapter(adapter)
                }, { println("${it.message}") })
                .addToDisposables()
    }

    override fun detachView() {
        adapter.clearDisposable()
        super.detachView()
    }
}