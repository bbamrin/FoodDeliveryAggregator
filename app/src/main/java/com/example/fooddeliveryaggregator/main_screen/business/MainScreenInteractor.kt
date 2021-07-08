package com.example.fooddeliveryaggregator.main_screen.business

import com.example.fooddeliveryaggregator.main_screen.model.ProductModel
import com.example.fooddeliveryaggregator.main_screen.model.SearchModel
import com.example.fooddeliveryaggregator.main_screen.model.ShopModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class MainScreenInteractor @Inject constructor() : IMainScreenInteractor {

    //Хранить имя и адрес тут - самое простое и быстрое, но далеко не лучшее решение
    //Можно было бы сделать репозиторий и работать с ним через интерактор, но сейчас мне лень + мало времени :)

    private lateinit var searchModel: SearchModel

    override fun setSearchInfo(searchModel: SearchModel) {
        this.searchModel = searchModel
    }

    override fun getSearchInfo() = searchModel

    override fun getShopList(searchModel: SearchModel): Single<List<ShopModel>> =
        Single.just(
            listOf(
                ShopModel(
                    "Утконос",
                    listOf(
                        ProductModel("лук зеленый", "200 Р"),
                        ProductModel("лук красный", "20 Р"),
                        ProductModel("лук синий", "10 Р"),
                        ProductModel("лук белый", "1000 Р"),
                    )
                ),
                ShopModel(
                    "Перекресток",
                    listOf(
                        ProductModel("лук оранжевый", "41 Р"),
                        ProductModel("лук розовый", "20 Р"),
                        ProductModel("лук серый", "10 Р"),
                        ProductModel("лук белый", "254 Р"),
                    )
                ),
                ShopModel(
                    "Лента",
                    listOf(
                        ProductModel("лук оранжевый", "411 Р"),
                        ProductModel("лук розовый", "201 Р"),
                        ProductModel("лук серый", "103 Р"),
                        ProductModel("лук белый", "4254 Р"),
                    )
                ),
                ShopModel(
                    "Окей",
                    listOf(
                        ProductModel("лук оранжевый", "411 Р"),
                        ProductModel("лук розовый", "200 Р"),
                        ProductModel("лук серый", "310 Р"),
                        ProductModel("лук белый", "2254 Р"),
                    )
                )
            )
        )

}