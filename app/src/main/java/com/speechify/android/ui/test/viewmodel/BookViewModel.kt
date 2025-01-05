package com.speechify.android.ui.test.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.speechify.android.ui.test.data.Book
import com.speechify.android.ui.test.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class BookViewModel : ViewModel() {

    private val repository by lazy { BookRepository() }

    fun fetchBooks(completion: (List<Book>) -> Unit) {
        viewModelScope.launch {
            completion(repository.getBooks())
        }
    }

    fun getBitmap(id: Int, completion: (Bitmap) -> Unit) {
        viewModelScope.launch {
            completion(getBookImageBitmap(id))
        }
    }

    private suspend fun loadBitmapFromURL(urlString: String): Bitmap {
        return withContext(Dispatchers.IO) {
            val url = URL(urlString)
            val connection = url.openConnection()
            connection.connect()
            val inputStream = connection.getInputStream()
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            bitmap
        }
    }

    private suspend fun getBookImageBitmap(bookId: Int): Bitmap {
        return loadBitmapFromURL("https://picsum.photos/400/400")
    }
}