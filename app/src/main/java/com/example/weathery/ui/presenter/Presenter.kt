package com.example.weathery.ui.presenter

import com.example.okhttp.model.WeatherResponse
import com.example.weathery.R
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class Presenter(val view: IView) {

    private val client = OkHttpClient()

    fun fetchData()
    {

            val request = Request.Builder().url("https://api.openweathermap.org/data/2.5/forecast?q=Berlin&appid=08c5136f4d4ac93d7f2e0d3d054a48ad").build()
            client.newCall(request).enqueue(object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.message?.let { view.showError(it) }
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body?.string().let {stringGson ->
                        val result = Gson().fromJson(stringGson, WeatherResponse::class.java)
                        if (response.isSuccessful)
                            result.list[0].main?.temp?.toInt()?.let { view.showData(result, it) }
                        else
                            view.showError("Failed to connect!")
                    }
                }

            })
    }


}