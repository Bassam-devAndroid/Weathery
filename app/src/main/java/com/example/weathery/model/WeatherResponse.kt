package com.example.okhttp.model
import com.example.okhttp.model.City
import com.example.okhttp.model.List
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("cod"     ) var cod     : String?         = null,
    @SerializedName("message" ) var message : Int?            = null,
    @SerializedName("cnt"     ) var cnt     : Int?            = null,
    @SerializedName("list"    ) var list    : ArrayList<List> = arrayListOf(),
    @SerializedName("city"    ) var city    : City?           = City()
)


