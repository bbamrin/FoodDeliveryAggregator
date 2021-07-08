package com.example.fooddeliveryaggregator.main_screen.presenter

import com.example.fooddeliveryaggregator.core.contracts.BasePresenter
import com.example.fooddeliveryaggregator.main_screen.business.IMainScreenInteractor
import com.example.fooddeliveryaggregator.main_screen.model.ShopModel
import com.example.fooddeliveryaggregator.main_screen.view.IMainScreenView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MainScreenPresenter @Inject constructor(
    private val mainScreenInteractor: IMainScreenInteractor
) : BasePresenter<IMainScreenView>(), IMainScreenPresenter {
    override fun onViewReady() {
        val searchInfo = mainScreenInteractor.getSearchInfo()
        compositeDisposable += mainScreenInteractor.getShopList(searchInfo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::processShopsData)
    }

    private fun processShopsData(shopList: List<ShopModel>) {
        view?.showShops(shopList)
    }

}