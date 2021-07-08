package com.example.fooddeliveryaggregator.main_screen.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryaggregator.R
import com.example.fooddeliveryaggregator.core.DiffItemCallback
import com.example.fooddeliveryaggregator.databinding.MainScreenFragmentBinding
import com.example.fooddeliveryaggregator.di.ComponentManager
import com.example.fooddeliveryaggregator.main_screen.delegates.shopDelegate
import com.example.fooddeliveryaggregator.main_screen.model.IItemModel
import com.example.fooddeliveryaggregator.main_screen.model.ShopModel
import com.example.fooddeliveryaggregator.main_screen.presenter.IMainScreenPresenter
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
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

    private lateinit var recyclerAdapter: AsyncListDifferDelegationAdapter<IItemModel>

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
        setupRecyclerView()
        presenter.bindView(this)
        presenter.onViewReady()
    }

    override fun onDestroyView() {
        presenter.unbindView()
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("keklol", "ondestroy")
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun setupRecyclerView() {
        recyclerAdapter = AsyncListDifferDelegationAdapter(
            DiffItemCallback(),
            shopDelegate()
        )
        binding.rvShops.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun showShops(shopList: List<ShopModel>) {
        recyclerAdapter.items = shopList
    }
}