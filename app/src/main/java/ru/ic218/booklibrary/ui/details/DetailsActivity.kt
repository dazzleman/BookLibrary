package ru.ic218.booklibrary.ui.details

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_details.*
import ru.ic218.booklibrary.AppDelegate
import ru.ic218.booklibrary.R
import ru.ic218.booklibrary.data.repository.DefaultRepository
import ru.ic218.booklibrary.model.db.BookEntity
import ru.ic218.booklibrary.ui.common.BaseActivity
import ru.ic218.booklibrary.ui.common.dialog.BookDialogFragment
import ru.ic218.booklibrary.ui.details.adapter.DetailsAdapter
import javax.inject.Inject

class DetailsActivity : BaseActivity(), DetailsContract.View {

    companion object {
        const val CATEGORY_ID = "category_id"
    }

    @Inject
    lateinit var defaultRepository: DefaultRepository

    private val presenter: DetailsContract.Presenter by lazy { DetailsPresenter(defaultRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        AppDelegate.getInstance(this).getComponent().inject(this)
        presenter.attachView(this)

        initRecycleView()
        btnCreateBook.setOnClickListener({presenter.createBook()})
        presenter.init(intent.getIntExtra(CATEGORY_ID, 0))
    }

    private fun initRecycleView() {
        rvDetails.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvDetails.setHasFixedSize(true)
    }

    override fun setAdapter(adapter: DetailsAdapter) {
        rvDetails.adapter = adapter
    }

    override fun showDialogAddBook(idBook: Int, idCategory: Int) {
        BookDialogFragment.newInstance(idBook, idCategory, true).show(supportFragmentManager, "BookDialogAdd")
    }

    override fun showDialogEditBook(book: BookEntity) {
        BookDialogFragment.newInstance(book.id, book.idCategory, false).show(supportFragmentManager, "BookDialogEdit")
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
