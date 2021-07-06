package com.example.fooddeliveryaggregator.main_screen.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fooddeliveryaggregator.R
import com.example.fooddeliveryaggregator.databinding.MainScreenFragmentBinding
import com.example.fooddeliveryaggregator.databinding.SearchFragmentBinding
import com.example.fooddeliveryaggregator.di.ComponentManager
import com.example.fooddeliveryaggregator.main_screen.presenter.IMainScreenPresenter
import com.example.fooddeliveryaggregator.search.presenter.ISearchPresenter
import com.example.fooddeliveryaggregator.search.view.SearchFragment
import javax.inject.Inject

class MainScreenFragment: Fragment(R.layout.main_screen_fragment), IMainScreenView {
    companion object {
        fun newInstance(): MainScreenFragment =
            MainScreenFragment()
    }

    private var _binding: MainScreenFragmentBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: IMainScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentManager.plusMainComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainScreenFragmentBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("keklol", "here")
        presenter.bindView(this)
        presenter.onViewReady()
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
}