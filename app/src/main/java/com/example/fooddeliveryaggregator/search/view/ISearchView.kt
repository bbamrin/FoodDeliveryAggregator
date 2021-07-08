package com.example.fooddeliveryaggregator.search.view

import android.location.Address
import com.example.fooddeliveryaggregator.core.contracts.IBaseView

interface ISearchView: IBaseView {
    fun openMainScreenFragment()
    fun getAddress(latitude: Double, longitude: Double): List<Address>
    fun requestLastKnownLocation()
    fun setAddress(address: String?)
}