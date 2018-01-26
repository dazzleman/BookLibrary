package ru.ic218.booklibrary.ui.common

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

interface BaseContract {

    interface View {

    }

    interface Presenter<in V : BaseContract.View> {

        fun attachView(view: V)

        fun detachView()
    }

}