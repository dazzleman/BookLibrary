package ru.ic218.booklibrary.data.repository

import ru.ic218.booklibrary.data.db.DbProvider
import ru.ic218.booklibrary.data.preferences.PreferenceProvider

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

class DefaultRepositoryImpl(
        private val dbProvider: DbProvider,
        private val preferenceProvider: PreferenceProvider): DefaultRepository {

}