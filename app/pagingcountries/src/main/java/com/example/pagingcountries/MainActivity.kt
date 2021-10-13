package com.example.pagingcountries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingcountries.adapter.CountryAdapter
import com.example.pagingcountries.utils.CountriesDb
import com.example.pagingcountries.viewmodel.MainViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        CountriesDb.initialize(this)
        val CountriesRV = findViewById<RecyclerView>(R.id.country_rec)
        var button   = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val adapter = CountryAdapter()
        var  topId = 1

        CountriesRV.layoutManager = LinearLayoutManager(this)
        CountriesRV.adapter = adapter

        val viewModel = MainViewModel()

        viewModel.countries.observe(this, {
           adapter.submitList(it)
        })

//        button.setOnClickListener {
//            viewModel.dataSource.deleteItem(1)
//
//        }

    }
}