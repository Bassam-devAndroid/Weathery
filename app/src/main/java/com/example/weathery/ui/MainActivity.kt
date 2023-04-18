package com.example.weathery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.okhttp.model.WeatherResponse
import com.example.weathery.R
import com.example.weathery.databinding.ActivityMainBinding
import com.example.weathery.ui.presenter.IView
import com.example.weathery.ui.presenter.Presenter


class MainActivity : AppCompatActivity(), IView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = Presenter(this)
        presenter.fetchData()

    }

    override fun showData(weatherResponse: WeatherResponse,temp: Int) {
        runOnUiThread {
            binding.textviewTemp.text = "${weatherResponse.list[0].main?.temp?.minus(273)?.toInt()}°C"
            binding.cityName.text = weatherResponse.city?.name
            binding.countryName.text = weatherResponse.city?.country
            binding.description.text = weatherResponse.list[0].weather[0].description
            weatherResponse.list[0].main?.temp?.minus(273)?.toInt()?.let { setImageFromTemp(it) }
        }
    }

    override fun showError(error: String) {
        Log.i("Main_Activity",error)
    }

    override fun setImageFromTemp(temp: Int) {
        val summerClothes = listOf(
            R.drawable.first_summer,
            R.drawable.second_summer,
            R.drawable.third_summer,
            R.drawable.fourth_summer,
            R.drawable.fifth_summer
        )
        val winterClothes = listOf(
            R.drawable.first_winter,
            R.drawable.second_winter,
            R.drawable.third_winter,
            R.drawable.fourth_winter,
            R.drawable.fifth_winter
        )

        val images = when{
            temp <= 18 -> winterClothes
            else -> summerClothes
        }

        val image = images.random()
        runOnUiThread {
            binding.clothePic.setImageDrawable(getDrawable(image))
        }
    }
}
