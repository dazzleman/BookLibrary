package ru.ic218.booklibrary.ui.main

import ru.ic218.booklibrary.ui.common.BaseContract
import ru.ic218.booklibrary.ui.main.adapter.MainAdapter

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

interface MainContract {

    interface View : BaseContract.View {
        fun setAdapter(adapter: MainAdapter)
        fun runActivity(id: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun init()
    }

}