package com.example.fooddeliveryaggregator.search.presenter

import android.util.Log
import com.example.fooddeliveryaggregator.core.contracts.BasePresenter
import com.example.fooddeliveryaggregator.di.MainScope
import com.example.fooddeliveryaggregator.main_screen.business.IMainScreenInteractor
import com.example.fooddeliveryaggregator.main_screen.model.SearchModel
import com.example.fooddeliveryaggregator.search.view.ISearchView
import javax.inject.Inject

@MainScope
class SearchPresenter @Inject constructor(
    private val mainScreenInteractor: IMainScreenInteractor
): BasePresenter<ISearchView>(), ISearchPresenter {

    override fun onSearchButtonClicked(searchModel: SearchModel) {
        Log.d("keklol", "click")
        mainScreenInteractor.setSearchInfo(searchModel)
        view?.openMainScreenFragment()
    }


}