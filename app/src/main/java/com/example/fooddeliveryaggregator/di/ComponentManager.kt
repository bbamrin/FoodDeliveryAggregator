package com.example.fooddeliveryaggregator.di

object ComponentManager {
    private var mainComponent: MainComponent? = null

    fun plusMainComponent(): MainComponent =
        mainComponent ?: DaggerMainComponent
            .builder()
            .build()
            .also { mainComponent = it}

    fun clearMainComponent() {
        mainComponent = null
    }
}