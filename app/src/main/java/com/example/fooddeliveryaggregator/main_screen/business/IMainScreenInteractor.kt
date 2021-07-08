package com.example.fooddeliveryaggregator.main_screen.business

import com.example.fooddeliveryaggregator.main_screen.model.SearchModel
import com.example.fooddeliveryaggregator.main_screen.model.ShopModel
import io.reactivex.rxjava3.core.Single

interface IMainScreenInteractor {

    fun setSearchInfo(searchModel: SearchModel)

    fun getSearchInfo(): SearchModel

    fun getShopList(searchModel: SearchModel): Single<List<ShopModel>>

}