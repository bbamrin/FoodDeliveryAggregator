package com.example.fooddeliveryaggregator.core.contracts

interface IBasePresenter<in T : IBaseView> {
    fun bindView(view: T)
    fun unbindView()
    fun onViewReady()
    fun onDestroy()
}