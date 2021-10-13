package com.example.pagingcountries.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.pagingcountries.model.Country
import com.example.pagingcountries.viewHolders.CountryViewHolder

class CountryAdapter : PagedListAdapter<Country, CountryViewHolder>(COUNTRY_DIFFUTIL_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
      return CountryViewHolder.createLayout(parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
       val country = getItem(position)
        country?.let {
            holder.bind(it)
        }

    }

    companion object {
        val COUNTRY_DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<Country>(){
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
               return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
               return oldItem.name == newItem.name
            }

        }
    }
}