package com.example.fooddeliveryaggregator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fooddeliveryaggregator.core.extensions.addFragment
import com.example.fooddeliveryaggregator.main_screen_host.view.MainScreenHostFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val MAIN_SCREEN_HOST_TAG = "MAIN_SCREEN_HOST_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            MainScreenHostFragment.javaClass.simpleName
            addFragment(fragment = MainScreenHostFragment.newInstance(), tag = MAIN_SCREEN_HOST_TAG)
        }
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentByTag(MAIN_SCREEN_HOST_TAG)
        fragment?.apply {
            if (childFragmentManager.backStackEntryCount > 1)
                childFragmentManager.popBackStack()
            else
                finish()
        }
    }

}