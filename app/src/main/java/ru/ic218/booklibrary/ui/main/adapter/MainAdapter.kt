package ru.ic218.booklibrary.ui.main.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.ic218.booklibrary.R
import ru.ic218.booklibrary.data.repository.DefaultRepository
import ru.ic218.booklibrary.model.db.CategoryEntity
import ru.ic218.booklibrary.utils.OnClickListener
import ru.ic218.booklibrary.utils.applySchedulers


/**
 * @author Nikolay Vlaskin on 06.02.2018.
 */

class MainAdapter(private val defaultRepository: DefaultRepository,
                  private val onItemClick: OnClickListener<Int>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var categories: ArrayList<CategoryEntity> = arrayListOf()
    private val compose: CompositeDisposable = CompositeDisposable()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_recycle_view, parent, false)
        return ViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount() = categories.size

    fun addCategories(categories: ArrayList<CategoryEntity>) {
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }

    fun clearDisposable() = compose.clear()
    private fun Disposable.addToDisposables() = compose.add(this)

    inner class ViewHolder(itemView: View,
                           private val onItemClick: OnClickListener<Int>) : RecyclerView.ViewHolder(itemView) {
        var nameTextView: TextView
        var btnYet: TextView
        var recycleSecond: RecyclerView

        init {
            nameTextView = itemView.findViewById(R.id.snapTextView)
            btnYet = itemView.findViewById(R.id.btnYet)
            recycleSecond = itemView.findViewById(R.id.rvSecond)
        }

        fun bind(category: CategoryEntity){
            nameTextView.text = category.name
            recycleSecond.layoutManager = LinearLayoutManager(recycleSecond.context, LinearLayoutManager.HORIZONTAL, false)
            btnYet.setOnClickListener({onItemClick.invoke(category.id)})
            itemView.setOnClickListener({onItemClick.invoke(category.id)})

            defaultRepository.getBooksFromDBByCategory(category.id)
                    .applySchedulers()
                    .subscribe({
                        recycleSecond.adapter = SecondAdapter(it)
                    })
                    .addToDisposables()
        }
    }
}