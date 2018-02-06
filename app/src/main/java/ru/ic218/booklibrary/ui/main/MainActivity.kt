package ru.ic218.booklibrary.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.ic218.booklibrary.AppDelegate
import ru.ic218.booklibrary.R
import ru.ic218.booklibrary.data.repository.DefaultRepository
import ru.ic218.booklibrary.ui.common.BaseActivity
import ru.ic218.booklibrary.ui.details.DetailsActivity
import ru.ic218.booklibrary.ui.main.adapter.MainAdapter
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    @Inject
    lateinit var defaultRepository: DefaultRepository

    private val presenter: MainContract.Presenter by lazy { MainPresenter(defaultRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppDelegate.getInstance(this).getComponent().inject(this)
        presenter.attachView(this)

        initRecycleView()
        presenter.init()
    }

    private fun initRecycleView() {
        rvMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvMain.setHasFixedSize(true)
    }

    override fun setAdapter(adapter: MainAdapter) {
        rvMain.adapter = adapter
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun runActivity(id: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.CATEGORY_ID, id)
        ActivityCompat.startActivity(this, intent, null)
    }
}
