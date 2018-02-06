package ru.ic218.booklibrary.ui.details.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.ic218.booklibrary.R
import ru.ic218.booklibrary.model.db.BookEntity
import ru.ic218.booklibrary.utils.OnClickListener

/**
 * @author Nikolay Vlaskin on 06.02.2018.
 */

class DetailsAdapter(private val onItemClick: OnClickListener<Int>) : RecyclerView.Adapter<DetailsAdapter.ViewHolder>()  {

    private var books: ArrayList<BookEntity> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_details_recycle_view, parent, false)
        return ViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = books[position]
        holder.bind(book)
    }

    fun addBooks(books: ArrayList<BookEntity>){
        this.books.addAll(books)
        notifyDataSetChanged()
    }

    override fun getItemCount() = books.size

    inner class ViewHolder(itemView: View,
                           private val onItemClick: OnClickListener<Int>) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var description: TextView

        init {
            title = itemView.findViewById(R.id.title)
            description = itemView.findViewById(R.id.description)
        }

        fun bind(book: BookEntity) {
            title.text = book.name
            description.text = book.description
            itemView.setOnClickListener({ onItemClick.invoke(book.id) })
        }
    }
}