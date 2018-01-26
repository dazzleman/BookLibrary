package ru.ic218.booklibrary.ui.main

import ru.ic218.booklibrary.ui.common.BaseContract

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

interface MainContract {

    interface View : BaseContract.View {

    }

    interface Presenter : BaseContract.Presenter<View> {
        fun init()
    }

}