package com.example.fooddeliveryaggregator.main_screen.business

import com.example.fooddeliveryaggregator.main_screen.model.SearchModel
import com.example.fooddeliveryaggregator.main_screen.model.ShopModel
import com.example.fooddeliveryaggregator.search.model.SuggestionRequestBody
import com.example.fooddeliveryaggregator.search.model.SuggestionResponseList
import io.reactivex.rxjava3.core.Single

interface IMainScreenInteractor {

    fun setSearchInfo(searchModel: SearchModel)

    fun getSearchInfo(): SearchModel

    fun getShopList(searchModel: SearchModel): Single<List<ShopModel>>

    fun getGeolocationSuggestions(requestBody: SuggestionRequestBody): Single<SuggestionResponseList>

}