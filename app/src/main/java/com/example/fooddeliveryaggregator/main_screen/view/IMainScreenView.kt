package com.example.fooddeliveryaggregator.main_screen.view

import com.example.fooddeliveryaggregator.core.contracts.IBaseView
import com.example.fooddeliveryaggregator.main_screen.model.ShopModel

interface IMainScreenView: IBaseView {
    fun showShops(shopList: List<ShopModel>)
}