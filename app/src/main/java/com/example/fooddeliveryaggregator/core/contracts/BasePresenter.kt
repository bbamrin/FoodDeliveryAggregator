package com.example.fooddeliveryaggregator.core.contracts

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BasePresenter<T: IBaseView>: IBasePresenter<T> {

    @JvmField
    protected var view: T? = null

    @JvmField
    protected val compositeDisposable = CompositeDisposable()

    override fun bindView(view: T) {
        this.view = view
    }

    override fun unbindView() {
        this.view = null
    }

    override fun onViewReady() {

    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    protected operator fun CompositeDisposable.plusAssign(subscribe: Disposable) {
        this.add(subscribe)
    }

    protected operator fun CompositeDisposable.minusAssign(subscribe: Disposable) {
        this.remove(subscribe)
    }
}