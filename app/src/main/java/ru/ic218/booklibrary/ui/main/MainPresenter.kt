package ru.ic218.booklibrary.ui.main

import ru.ic218.booklibrary.data.repository.DefaultRepository
import ru.ic218.booklibrary.ui.common.BasePresenter

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

class MainPresenter(private val defaultRepository: DefaultRepository)
    : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun init() {

    }

}