package com.example.fooddeliveryaggregator.search.presenter

import android.location.Location
import com.example.fooddeliveryaggregator.core.contracts.BasePresenter
import com.example.fooddeliveryaggregator.di.MainScope
import com.example.fooddeliveryaggregator.main_screen.business.IMainScreenInteractor
import com.example.fooddeliveryaggregator.main_screen.model.SearchModel
import com.example.fooddeliveryaggregator.search.model.SuggestionRequestBody
import com.example.fooddeliveryaggregator.search.model.SuggestionResponseList
import com.example.fooddeliveryaggregator.search.view.ISearchView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@MainScope
class SearchPresenter @Inject constructor(
    private val mainScreenInteractor: IMainScreenInteractor
): BasePresenter<ISearchView>(), ISearchPresenter {

    override fun onSearchButtonClicked(searchModel: SearchModel) {
        mainScreenInteractor.setSearchInfo(searchModel)
        view?.openMainScreenFragment()
    }

    override fun onLocationGranted(location: Location?) {
        location?.let {
            val address = view?.getAddress(it.latitude, it.longitude)
            var addressString: String? = null
            address?.get(0)?.getAddressLine(0)?.split(",")?.let {
                addressString = it[0] + ", " + it[1]
            }
            view?.setAddress(addressString)
        }
    }

    //не уверен, что идея плодить диспосаблы на каждый введенный символ очень хорошая,
    //но пока оставлю так, т.к очень поверхностно знаю rxJava2, при рефакторинге возможно поменяю
    override fun onGeolocationTextChanged(text: String) {
        compositeDisposable += mainScreenInteractor.getGeolocationSuggestions(
            SuggestionRequestBody(
                text
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::setGeolocationSuggestions)
    }

    private fun setGeolocationSuggestions(suggestions: SuggestionResponseList) {
        suggestions.suggestions?.mapNotNull { it.value }?.apply {
            view?.setSuggestions(this)
        }
    }

}