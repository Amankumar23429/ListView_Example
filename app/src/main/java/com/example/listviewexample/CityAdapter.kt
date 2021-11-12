package com.example.listviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.util.concurrent.CountDownLatch

class CityAdapter(val cityData: Array<City>) : BaseAdapter() {
    override fun getCount(): Int {
        return cityData.size
    }

    override fun getItem(position: Int): City {
        return cityData[position]
    }

    override fun getItemId(position: Int): Long {
        return cityData[position].name.hashCode().toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val cityView: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            cityView =
                LayoutInflater.from(parent?.context).inflate(R.layout.city_item, parent, false)
            viewHolder = ViewHolder()
            with(viewHolder) {
                cityCountry = cityView.findViewById(R.id.city_country)
                cityName = cityView.findViewById(R.id.city_name)
                cityView.tag = this
            }
        } else {
            cityView = convertView
            viewHolder = convertView.tag as ViewHolder
        }

        with(viewHolder) {
            val city = getItem(position)
            cityCountry.text = getItem(position).country
            cityName.text = getItem(position).name

            if (city.favourite) {
                cityView.setBackgroundColor(
                    ContextCompat.getColor(
                        cityView.context,
                        R.color.color_favourite
                    )
                )
            } else {
                cityView.setBackgroundColor(ContextCompat.getColor(cityView.context, R.color.white))
            }
        }
        return cityView
    }

    private class ViewHolder {
        lateinit var cityCountry: TextView
        lateinit var cityName: TextView
    }

}
