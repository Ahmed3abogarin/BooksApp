package com.speechify.android.ui.test.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.speechify.android.ui.test.R
import com.speechify.android.ui.test.adapter.BookAdapter
import com.speechify.android.ui.test.data.Book
import com.speechify.android.ui.test.databinding.FragmentBookListBinding
import com.speechify.android.ui.test.viewmodel.BookViewModel

class BookListFragment : Fragment() {

    private var _binding: FragmentBookListBinding? = null
    private val binding get() = _binding!!

    private val bookViewModel: BookViewModel by activityViewModels()
    private val booksAdapter by lazy {
        BookAdapter(bookViewModel::getBitmap, this::onBookClicked)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookListBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBookList.adapter = booksAdapter
        bookViewModel.fetchBooks {
            booksAdapter.setItems(it)
        }
    }

    private fun onBookClicked(book: Book) {
        findNavController().navigate(
            R.id.action_bookListFragment_to_bookDetailsFragment,
            bundleOf(BookDetailsFragment.BOOK_KEY to book)
        )
    }
}