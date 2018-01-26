package ru.ic218.booklibrary.utils

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

fun <T> Observable<T>.applySchedulers(
        subscribeOn: Scheduler = Schedulers.io(),
        observeOn: Scheduler = AndroidSchedulers.mainThread()): Observable<T> {
    return this
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
}