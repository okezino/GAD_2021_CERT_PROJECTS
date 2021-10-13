package com.example.pagingcountries.viewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingcountries.R
import com.example.pagingcountries.model.Country

class CountryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val country : TextView= itemView.findViewById(R.id.country_name)
    val population : TextView= itemView.findViewById(R.id.population_id)

    fun bind(countryItem: Country){
        country.text = countryItem.name
        population.text = countryItem.population
    }

    companion object{
        fun createLayout(parent : ViewGroup) : CountryViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent,false)

            return CountryViewHolder(view)
        }
    }
}