package com.example.pagingcountries.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.pagingcountries.datasources.PageCountriesDatasourceFactory
import com.example.pagingcountries.model.Country
import com.example.pagingcountries.utils.CountryBoundaryCallBack

class MainViewModel : ViewModel() {

    private val pageSize = 15
    val config = PagedList.Config.Builder()
        .setPageSize(pageSize)
        .setInitialLoadSizeHint(15)
        .setPrefetchDistance(5)
        .setEnablePlaceholders(false)
        .build()

    var dataSource = PageCountriesDatasourceFactory()
//    var countries : LiveData<PagedList<Country>> = LivePagedListBuilder(dataSource,config)
//        .setBoundaryCallback(CountryBoundaryCallBack())
//        .build()

    var countries : LiveData<PagedList<Country>> = dataSource.toLiveData(config)

}