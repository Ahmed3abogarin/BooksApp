package com.speechify.android.ui.test.repository

import com.speechify.android.ui.test.data.Book

class BookRepository {

    fun getBooks(): List<Book> {
        return listOf(
            Book(id = 1, title = "The Great Gatsby", author = "F. Scott Fitzgerald"),
            Book(id = 2, title = "The Da Vinci Code", author = "Dan Brown"),
            Book(id = 3, title = "The Catcher in the Rye", author = "J. D. Salinger"),
            Book(id = 4, title = "The Hobbit", author = "J. R. R. Tolkien"),
            Book(id = 5, title = "The Hunger Games", author = "Suzanne Collins"),
            Book(id = 6, title = "The Kite Runner", author = "Khaled Hosseini"),
        )
    }
}