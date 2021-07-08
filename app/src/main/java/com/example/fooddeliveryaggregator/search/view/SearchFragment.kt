package com.example.fooddeliveryaggregator.search.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fooddeliveryaggregator.R
import com.example.fooddeliveryaggregator.databinding.SearchFragmentBinding
import com.example.fooddeliveryaggregator.di.ComponentManager
import com.example.fooddeliveryaggregator.main_screen.model.SearchModel
import com.example.fooddeliveryaggregator.main_screen.view.MainScreenFragment
import com.example.fooddeliveryaggregator.search.presenter.ISearchPresenter
import javax.inject.Inject

class SearchFragment: Fragment(R.layout.search_fragment), ISearchView {

    companion object {
        fun newInstance(): SearchFragment =
            SearchFragment()
    }

    private var _binding: SearchFragmentBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: ISearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentManager.plusMainComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.bindView(this)
        presenter.onViewReady()
        binding.btnSearch.setOnClickListener {
            Log.d("keklol", "click")
            presenter.onSearchButtonClicked(
                SearchModel(
                    binding.etGeolocation.editText?.text.toString(),
                    binding.etProductName.editText?.text.toString()
                )
            )
        }
    }

    override fun onDestroyView() {
        presenter.unbindView()
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun openMainScreenFragment() {
        requireParentFragment().childFragmentManager.commit {
            replace(R.id.fmt_internal_container, MainScreenFragment.newInstance())
            addToBackStack(null)
        }
    }
}