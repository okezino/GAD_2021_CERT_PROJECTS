package com.example.pagingcountries.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.pagingcountries.model.Country
import com.example.pagingcountries.utils.CountriesDb


class PageCountriesDatasource : PageKeyedDataSource<Int, Country>() {

    private  val TAG = "PageCountriesDatasource"
    private val source = CountriesDb.getCountries()

    fun deleteItem(itemId : Int){
        CountriesDb.deleteCountryById(itemId)
        invalidate()
    }
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Country>) {
        val pageCountries = source.filter { country -> country.id == 1 }
        callback.onResult(pageCountries,null,2)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Country>) {
        val list = source.filter { country -> country.id == params.key }
        callback.onResult(list, params.key - 1 )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Country>) {
        val list = source.filter { country -> country.id == params.key }
        callback.onResult(list, params.key + 1 )
    }
}

class  PageCountriesDatasourceFactory : DataSource.Factory<Int,Country>(){
    private val datasource =  MutableLiveData<PageCountriesDatasource>()
    lateinit var latestDatasource: PageCountriesDatasource

    fun deleteItem(itemId : Int){
        latestDatasource.deleteItem(itemId)
    }

    override fun create(): DataSource<Int, Country> {
        latestDatasource = PageCountriesDatasource()
        datasource.postValue(latestDatasource)
        return latestDatasource

    }

}