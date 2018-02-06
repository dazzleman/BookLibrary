package ru.ic218.booklibrary.ui.main.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.ic218.booklibrary.R
import ru.ic218.booklibrary.data.repository.DefaultRepository
import ru.ic218.booklibrary.model.db.CategoryEntity
import ru.ic218.booklibrary.utils.applySchedulers


/**
 * @author Nikolay Vlaskin on 06.02.2018.
 */

class MainAdapter(private val defaultRepository: DefaultRepository) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var categories: ArrayList<CategoryEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.nameTextView.text = category.name
        holder.recycleSecond.layoutManager = LinearLayoutManager(holder.recycleSecond.context, LinearLayoutManager.HORIZONTAL, false)

        defaultRepository.getBooksFromDBByCategory(category.id)
                .applySchedulers()
                .subscribe({
                    holder.recycleSecond.adapter = SecondAdapter(it)
                })
    }

    override fun getItemCount() = categories.size

    fun addCategories(categories: ArrayList<CategoryEntity>) {
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var nameTextView: TextView
        var recycleSecond: RecyclerView

        init {
            itemView.setOnClickListener(this)
            nameTextView = itemView.findViewById(R.id.snapTextView)
            recycleSecond = itemView.findViewById(R.id.rvSecond)
        }

        override fun onClick(v: View) {
            println("Position main adapter: $adapterPosition")
        }
    }
}