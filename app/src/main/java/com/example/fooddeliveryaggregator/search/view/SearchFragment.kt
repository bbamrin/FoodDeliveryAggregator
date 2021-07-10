package com.example.fooddeliveryaggregator.search.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.fooddeliveryaggregator.R
import com.example.fooddeliveryaggregator.databinding.SearchFragmentBinding
import com.example.fooddeliveryaggregator.di.ComponentManager
import com.example.fooddeliveryaggregator.main_screen.model.SearchModel
import com.example.fooddeliveryaggregator.main_screen.view.MainScreenFragment
import com.example.fooddeliveryaggregator.search.presenter.ISearchPresenter
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.*
import javax.inject.Inject

class SearchFragment: Fragment(R.layout.search_fragment), ISearchView {

    companion object {
        fun newInstance(): SearchFragment =
            SearchFragment()
    }

    private var _binding: SearchFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var geocoder: Geocoder

    private lateinit var locationManager: LocationManager

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
    }

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private lateinit var autocompleteArrayAdapter: ArrayAdapter<String>

    private val autocompleteTextList: List<String> = mutableListOf()

    @Inject
    lateinit var presenter: ISearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentManager.plusMainComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchFragmentBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNavigationPermissions()
        setUpLocationUtils()

        autocompleteArrayAdapter = NoFilterArrayAdapter(
            requireContext(),
            R.layout.autocomplete_item,
            autocompleteTextList
        )
        binding.etGeolocationAutocomplete.setAdapter(autocompleteArrayAdapter)


        binding.btnSearch.setOnClickListener {
            presenter.onSearchButtonClicked(
                SearchModel(
                    binding.etGeolocation.editText?.text.toString(),
                    binding.etProductName.editText?.text.toString()
                )
            )
        }

        binding.etGeolocation.setEndIconOnClickListener {
            requestLastKnownLocation()
        }



        binding.etGeolocationAutocomplete.doOnTextChanged { text, start, before, count ->
            text?.let {
                if (it.isNotEmpty()) presenter.onGeolocationTextChanged(text.toString())
            }
        }

        presenter.bindView(this)
        presenter.onViewReady()
    }

    override fun onDestroyView() {
        presenter.unbindView()
        locationManager.removeUpdates(locationListener)
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun openMainScreenFragment() {
        requireParentFragment().childFragmentManager.commit {
            replace(R.id.fmt_internal_container, MainScreenFragment.newInstance())
            addToBackStack(null)
        }
    }


    override fun getAddress(latitude: Double, longitude: Double): List<Address> {
        return geocoder.getFromLocation(latitude, longitude, 1)
    }

    @SuppressLint("MissingPermission")
    override fun requestLastKnownLocation() {
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0,
            1f,
            locationListener
        )
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location == null)
                    Toast.makeText(
                        requireContext(),
                        "Не удалось получить вашу геолокацию",
                        Toast.LENGTH_SHORT
                    ).show()
                presenter.onLocationGranted(location)
            }
    }

    override fun setAddress(address: String?) {
        binding.etGeolocation.editText?.setText(address)
    }

    override fun setSuggestions(suggestions: List<String>) {
        autocompleteArrayAdapter.clear()
        autocompleteArrayAdapter.addAll(suggestions)
    }

    private fun checkNavigationPermissions() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                1
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun setUpLocationUtils() {
        geocoder = Geocoder(requireContext(), Locale.getDefault())
        locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        locationManager.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            0,
            1000f,
            locationListener
        )
    }
}