package com.example.fooddeliveryaggregator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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


    //todo
    //1) Добавить итем магазина в виде виджета + ресайклер с магазинами + подключить либу адаптер делегатов
    //2) Добавить "репозиторий" для передачи геоданных и названия продукта на главный экран (скорее всего все будет передаваться тупо через интерактор)
    //3) Создать модели продуктов и магазинов + добавить способ получения данных с сервера(подключить ретрофит + создать методы в интеракторе)
    //4) Сделать шиммеринг
    //5) Сделать подстановку адреса используя геолокацию

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