package com.speechify.android.ui.test.fragments

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.speechify.android.ui.test.data.Book
import com.speechify.android.ui.test.databinding.FragmentBookDetailsBinding
import com.speechify.android.ui.test.viewmodel.BookViewModel

class BookDetailsFragment : Fragment() {

    private var _binding: FragmentBookDetailsBinding? = null
    private val binding get() = _binding!!

    private val bookViewModel: BookViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookDetailsBinding.inflate(
            inflater, container, false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            it.parcelable<Book>(BOOK_KEY)?.let { book ->
                binding.book = book
                bookViewModel.getBitmap(book.id) { bitmap ->
                    binding.ivBookCover.setImageBitmap(bitmap)
                }
            }
        }
    }

    companion object {
        const val BOOK_KEY = "BOOK"
    }
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}