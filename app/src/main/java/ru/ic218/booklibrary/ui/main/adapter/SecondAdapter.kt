package ru.ic218.booklibrary.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.ic218.booklibrary.R
import ru.ic218.booklibrary.model.db.BookEntity

/**
 * @author Nikolay Vlaskin on 06.02.2018.
 */

class SecondAdapter(private val books: List<BookEntity>) : RecyclerView.Adapter<SecondAdapter.ViewHolder>() {

    private val booksArray: ArrayList<BookEntity> = arrayListOf()

    init {
        booksArray.addAll(books)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_second_recycle_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = booksArray[position]
        holder.title.text = book.name
    }

    override fun getItemCount() = booksArray.size

    fun addBook(book: BookEntity) = booksArray.add(book)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var image: ImageView
        var title: TextView

        init {
            itemView.setOnClickListener(this)
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
        }

        override fun onClick(v: View) {
            println("Position second adapter: $adapterPosition")
        }
    }
}