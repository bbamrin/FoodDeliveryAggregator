package com.example.fooddeliveryaggregator.search.presenter

import android.location.Location
import com.example.fooddeliveryaggregator.core.contracts.IBasePresenter
import com.example.fooddeliveryaggregator.main_screen.model.SearchModel
import com.example.fooddeliveryaggregator.search.view.ISearchView

interface ISearchPresenter : IBasePresenter<ISearchView> {

    fun onSearchButtonClicked(searchModel: SearchModel)
    fun onLocationGranted(location: Location?)
    fun onGeolocationTextChanged(text: String)

}