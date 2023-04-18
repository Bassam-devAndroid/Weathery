package com.example.weathery.ui.presenter

import com.example.okhttp.model.WeatherResponse

interface IView{
    fun showData(weatherResponse: WeatherResponse,temp: Int)
    fun showError(error: String)
    fun setImageFromTemp(temp: Int)
}