package com.example.pagingcountries.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.example.pagingcountries.model.Country
import com.example.pagingcountries.utils.CountriesDb


class KeyCountriesDatasource : ItemKeyedDataSource<Int, Country>() {

    private val source = CountriesDb.getCountries()

    override fun getKey(item: Country): Int {
        return item.id
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Country>
    ) {
        var list: MutableList<Country> = mutableListOf()

        for (i in 0..params.requestedLoadSize) {
            if (i > source.size) break
            list.add(source[i])

        }

        callback.onResult(list, 0, list.size)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Country>) {
        var key = params.key
        var list: MutableList<Country> = mutableListOf()
        if(key == source.size){
            callback.onResult(list.orEmpty())
            return
        }

        val lastCountry = source[key]

        for(i in lastCountry.id..(lastCountry.id + params.requestedLoadSize)){
            if(i == source.size) break
            list.add(source[i])
    }
        callback.onResult(list.orEmpty())

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Country>) {
        var key = params.key
        var list: MutableList<Country> = mutableListOf()
        if(key <= 0){
            callback.onResult(list.orEmpty())
            return
        }
        callback.onResult(list)
    }
}

class KeyCountriesDatasourceFactory : DataSource.Factory<Int, Country>(){
    private val datasource = MutableLiveData<KeyCountriesDatasource>()
    lateinit var latestDatasource: KeyCountriesDatasource

    override fun create(): DataSource<Int, Country> {
        latestDatasource = KeyCountriesDatasource()
        datasource.postValue(latestDatasource)
        return latestDatasource
    }

}