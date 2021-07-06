package com.example.fooddeliveryaggregator.search.presenter

import com.example.fooddeliveryaggregator.core.contracts.BasePresenter
import com.example.fooddeliveryaggregator.di.MainScope
import com.example.fooddeliveryaggregator.search.view.ISearchView
import javax.inject.Inject

@MainScope
class SearchPresenter @Inject constructor(

): BasePresenter<ISearchView>(), ISearchPresenter {

}