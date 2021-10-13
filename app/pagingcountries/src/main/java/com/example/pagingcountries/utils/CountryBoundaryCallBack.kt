package com.example.pagingcountries.utils

import android.util.Log
import androidx.paging.PagedList
import com.example.pagingcountries.model.Country

class CountryBoundaryCallBack : PagedList.BoundaryCallback<Country>() {
    /**
     * This is called when there is no data onthe datasource to load
     */
    override fun onZeroItemsLoaded() {
        Log.i("Country Boundary" , "no item in the data source")
    }

    /**
     * This is called when the application has consume all the data, so more data is needed from the API
     */

    override fun onItemAtEndLoaded(itemAtEnd: Country) {
        Log.i("Country Boundary" , "all item has been loaded")
    }

    /**
     * This is called when the first item is called and shown on the interface
     */

    override fun onItemAtFrontLoaded(itemAtFront: Country) {
        Log.i("Country Boundary" , "first item has been loaded")
    }
}