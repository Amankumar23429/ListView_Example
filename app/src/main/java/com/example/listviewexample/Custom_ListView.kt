package com.example.listviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView


class Custom_ListView : AppCompatActivity() {
    val cityData = fillCitydata()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list_view)

        val cities = findViewById<ListView>(R.id.list_view2)

        val cityAdapter = CityAdapter(cityData)
        cities.adapter = cityAdapter

        cities.setOnItemClickListener { parent, view, position, id ->
            val city:City? = cityAdapter.getItem(position)
            city?.let {
                city.favourite = !city.favourite
                cityAdapter.notifyDataSetChanged()
            }

        }

    }

    private fun fillCitydata():Array<City>{
        return arrayOf(City("USA","New York"),
            City("India","New Delhi"),
            City("France","Paris"),
            City("Italy","Rome"),
            City("Netherlands","Amsterdam"))
    }
}