package com.example.fooddeliveryaggregator.search.model

import com.google.gson.annotations.Expose

data class Suggestion(
    var value: String? = null,

    @Expose(serialize = false, deserialize = false)
    @Transient
    var unrestrictedValue: String? = null,

    @Transient
    @Expose(serialize = false, deserialize = false)
    var data: List<Any>? = null
)
