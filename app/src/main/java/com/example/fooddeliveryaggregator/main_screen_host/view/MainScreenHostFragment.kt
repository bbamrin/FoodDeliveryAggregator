package com.example.fooddeliveryaggregator.main_screen_host.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fooddeliveryaggregator.R
import com.example.fooddeliveryaggregator.databinding.MainHostFragmentBinding
import com.example.fooddeliveryaggregator.databinding.SearchFragmentBinding
import com.example.fooddeliveryaggregator.di.ComponentManager
import com.example.fooddeliveryaggregator.main_screen.presenter.IMainScreenPresenter
import com.example.fooddeliveryaggregator.main_screen_host.presenter.IMainScreenHostPresenter
import com.example.fooddeliveryaggregator.search.presenter.ISearchPresenter
import com.example.fooddeliveryaggregator.search.view.SearchFragment
import javax.inject.Inject

class MainScreenHostFragment: Fragment(R.layout.main_host_fragment), IMainScreenHostView {


    companion object {
        fun newInstance(): MainScreenHostFragment =
            MainScreenHostFragment()
    }


    private var _binding: MainHostFragmentBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: IMainScreenHostPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentManager.plusMainComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainHostFragmentBinding.inflate(inflater,container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.bindView(this)
        presenter.onViewReady()
        if (savedInstanceState == null) {
            childFragmentManager.commit {
                add(R.id.fmt_internal_container, SearchFragment.newInstance())
                addToBackStack("kek")
            }
        }
    }

    override fun onDestroyView() {
        presenter.unbindView()
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        ComponentManager.clearMainComponent()
        presenter.onDestroy()
        super.onDestroy()
    }
}