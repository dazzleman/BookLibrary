package ru.ic218.booklibrary.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import ru.ic218.booklibrary.data.db.DbProvider
import ru.ic218.booklibrary.data.preferences.PreferenceProvider
import ru.ic218.booklibrary.model.db.BookEntity
import ru.ic218.booklibrary.model.db.CategoryEntity

/**
 * @author Nikolay Vlaskin on 26.01.2018.
 */

class DefaultRepositoryImpl(
        private val dbProvider: DbProvider,
        private val preferenceProvider: PreferenceProvider): DefaultRepository {

    override var isFirstStartupApplication: Boolean
        get() = preferenceProvider.isFirstStartupApplication
        set(value) {preferenceProvider.isFirstStartupApplication = value}

    override fun createDataAndSaveDB(): Single<Unit> {
        return Single.fromCallable {
            dbProvider.saveCategories(generateCategory())
            dbProvider.saveBooks(generateBooks())
        }
    }

    private fun generateCategory(): ArrayList<CategoryEntity>{
        return arrayListOf(
                CategoryEntity(0, "Программирование в интернет"),
                CategoryEntity(1, "Java для начинающих.")
        )
    }

    private fun generateBooks(): ArrayList<BookEntity>{
        return arrayListOf(
                BookEntity(0, 0, "Angular и TypeScript.", "testqwet"),
                BookEntity(1, 0, "ECMAScript 6", "testqwet"),
                BookEntity(2, 0, "Front-end", "testqwet"),
                BookEntity(3, 0, "Head First. Изучаем Ruby", "testqwet"),
                BookEntity(4, 0, "HTML5 и CSS3.", "testqwet"),
                BookEntity(5, 0, "Java для начинающих.", "testqwet"),
                BookEntity(6, 1, "Android.", "testqwet"),
                BookEntity(7, 1, "ASP.NET Core.", "testqwet"),
                BookEntity(8, 1, "Microsoft Visual C#.", "testqwet"),
                BookEntity(9, 1, "Python для сложных задач", "testqwet"),
                BookEntity(10, 1, "React.js", "testqwet"),
                BookEntity(11, 1, "React и Redux", "testqwet"))
    }

    override fun getBooksFromDBByCategory(idCategory: Int): Flowable<List<BookEntity>> {
        return dbProvider.getBooksFromCategory(idCategory)
    }

    override fun getCategories(): Flowable<List<CategoryEntity>> {
        return dbProvider.getCategories()
    }

    override fun addBook(book: BookEntity) {
        dbProvider.addBook(book)
    }
}