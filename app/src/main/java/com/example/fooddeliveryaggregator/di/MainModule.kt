package com.example.fooddeliveryaggregator.di

import com.example.fooddeliveryaggregator.main_screen.presenter.IMainScreenPresenter
import com.example.fooddeliveryaggregator.main_screen.presenter.MainScreenPresenter
import com.example.fooddeliveryaggregator.main_screen_host.presenter.IMainScreenHostPresenter
import com.example.fooddeliveryaggregator.main_screen_host.presenter.MainScreenHostPresenter
import com.example.fooddeliveryaggregator.search.presenter.ISearchPresenter
import com.example.fooddeliveryaggregator.search.presenter.SearchPresenter
import dagger.Binds
import dagger.Module

//will do separation on smaller modules when the time comes
@Module
interface MainModule {

    @Binds
    @MainScope
    fun bindsMainScreenHostPresenter(impl: MainScreenHostPresenter): IMainScreenHostPresenter

    @Binds
    @MainScope
    fun bindsSearchPresenter(impl: SearchPresenter): ISearchPresenter


    @Binds
    @MainScope
    fun bindsMainScreenPresenter(impl: MainScreenPresenter): IMainScreenPresenter

}