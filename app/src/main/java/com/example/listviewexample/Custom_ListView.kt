package com.example.listviewexample

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView


class Custom_ListView : AppCompatActivity() {
    val FAVOURITE_KEY = "FAVOURITE"
    val cityData = fillCitydata()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_list_view)
        loadFavourite()

        val cities = findViewById<ListView>(R.id.list_view2)

        val cityAdapter = CityAdapter(cityData)
        cities.adapter = cityAdapter

        cities.setOnItemClickListener { parent, view, position, id ->
            val city:City? = cityAdapter.getItem(position)
            city?.let {
                city.favourite = !city.favourite
                cityAdapter.notifyDataSetChanged()
            }
            saveFavourites()

        }

    }

    private fun saveFavourites(){
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val favourites = cityData.filter { it.favourite }.map { it.country }
        with(sharedPref.edit()){
            putStringSet(FAVOURITE_KEY, favourites.toSet())
            commit()
        }
    }

    private fun loadFavourite(){
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val favourites = sharedPref.getStringSet(FAVOURITE_KEY, null)

        favourites?.forEach{country->
            val city = cityData.find{it.country==country}
            city?.favourite=true
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