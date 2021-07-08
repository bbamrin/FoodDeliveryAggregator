package com.example.fooddeliveryaggregator.main_screen.delegates

import com.example.fooddeliveryaggregator.databinding.ProductItemBinding
import com.example.fooddeliveryaggregator.main_screen.model.IItemModel
import com.example.fooddeliveryaggregator.main_screen.model.ProductModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun productDelegate() =
    adapterDelegateViewBinding<ProductModel, IItemModel, ProductItemBinding>(
        viewBinding = { layoutInflater, parent ->
            ProductItemBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        },
    ) {
        bind {
            binding.tvProductName.text = item.name
            binding.tvProductPrice.text = item.price
        }
    }