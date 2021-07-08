package com.example.fooddeliveryaggregator.main_screen.delegates

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fooddeliveryaggregator.core.DiffItemCallback
import com.example.fooddeliveryaggregator.databinding.ShopItemBinding
import com.example.fooddeliveryaggregator.main_screen.model.IItemModel
import com.example.fooddeliveryaggregator.main_screen.model.ShopModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun shopDelegate() =
    adapterDelegateViewBinding<ShopModel, IItemModel, ShopItemBinding>(
        viewBinding = { layoutInflater, parent ->
            ShopItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        },
    ) {
        bind {
            binding.tvShopName.text = item.name
            val rvAdapter = AsyncListDifferDelegationAdapter<IItemModel>(
                DiffItemCallback(),
                productDelegate()
            )
            rvAdapter.items = item.products
            binding.rvProductCard.apply {
                adapter = rvAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }