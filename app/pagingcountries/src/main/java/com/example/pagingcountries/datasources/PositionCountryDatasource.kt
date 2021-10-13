package com.example.pagingcountries.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.example.pagingcountries.model.Country
import com.example.pagingcountries.utils.CountriesDb


class PositionCountryDatasource : PositionalDataSource<Country>() {

    var source = CountriesDb.getCountries()
    var batchSize = 0
     override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Country>) {
       batchSize = params.requestedLoadSize
         val list = mutableListOf<Country>()
         for(i in params.requestedStartPosition..params.requestedLoadSize){
             if(i == source.size) break

             list.add(source[i])
         }

         callback.onResult(list.orEmpty(), params.requestedStartPosition, list.size)

    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Country>) {
        val list = mutableListOf<Country>()
        for(i in params.startPosition..(params.startPosition + batchSize)){
            if(i == source.size) break
            list.add(source[i])
        }

        callback.onResult(list.orEmpty())
    }
}

class  PositionalCountryDatasourceFactory : DataSource.Factory<Int,Country>(){

    private val datasource = MutableLiveData<PositionCountryDatasource>()
    lateinit var latestDatasource: PositionCountryDatasource
    override fun create(): DataSource<Int, Country> {
        latestDatasource = PositionCountryDatasource()
        datasource.postValue(latestDatasource)
        return latestDatasource
    }

}