package com.speechify.android.ui.test.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.speechify.android.ui.test.R
import com.speechify.android.ui.test.data.Book
import com.speechify.android.ui.test.databinding.ItemBookBinding

class BookAdapter(
    private val getBitmap: (Int, (Bitmap) -> Unit) -> Unit,
    private val onItemClicked: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private val books = mutableListOf<Book>()

    inner class ViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.apply {
                data = book
                getBitmap(book.id) {
                    imageViewBookCover.setImageBitmap(it)
                }
                root.setOnClickListener { onItemClicked(book) }
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemBookBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_book,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(books[position])
    }

    fun setItems(books: List<Book>) {
        this.books.clear()
        this.books.addAll(books)
        notifyDataSetChanged()
    }
}