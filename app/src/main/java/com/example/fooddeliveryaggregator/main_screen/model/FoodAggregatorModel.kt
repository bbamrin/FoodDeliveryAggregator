package com.example.fooddeliveryaggregator.main_screen.model

interface IItemModel

data class SearchModel(
    val address: String,
    val name: String
) : IItemModel

data class ProductModel(
    val name: String,
    val price: String
) : IItemModel

data class ShopModel(
    val name: String,
    val products: List<ProductModel>
) : IItemModel