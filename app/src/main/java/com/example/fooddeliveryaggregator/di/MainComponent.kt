package com.example.fooddeliveryaggregator.di

import com.example.fooddeliveryaggregator.main_screen.presenter.IMainScreenPresenter
import com.example.fooddeliveryaggregator.main_screen.view.MainScreenFragment
import com.example.fooddeliveryaggregator.main_screen_host.presenter.IMainScreenHostPresenter
import com.example.fooddeliveryaggregator.main_screen_host.view.MainScreenHostFragment
import com.example.fooddeliveryaggregator.search.presenter.ISearchPresenter
import com.example.fooddeliveryaggregator.search.view.SearchFragment
import dagger.Component

@MainScope
@Component(modules = [MainModule::class])
interface MainComponent {

    fun mainScreenHostPresenter(): IMainScreenHostPresenter

    fun searchPresenter(): ISearchPresenter

    fun mainScreenPresenter(): IMainScreenPresenter

    fun inject(fragment: MainScreenHostFragment)

    fun inject(fragment: SearchFragment)

    fun inject(fragment: MainScreenFragment)
}