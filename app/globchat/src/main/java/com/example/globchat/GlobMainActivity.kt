package com.example.globchat

import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager

class GlobMainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    val TAG = "MainActivity"
    lateinit var navController : NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glob_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)

        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(this)

        val autoTime = sharedPreference.getString("key_auto_reply_time", "")
        val autoDownload = sharedPreference.getBoolean("key_auto_download",false)

        Log.i(TAG,"auto key time $autoTime")
        Log.i(TAG,"auto key download $autoDownload")


        }

    override fun onResume() {
        super.onResume()

        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
    }

    override fun onStop() {
        super.onStop()
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onSharedPreferenceChanged(sharedPreferences:SharedPreferences?, key: String?) {
        if(key == "key_auto_status"){

            val newStatus = sharedPreferences?.getString("auto_status","")
            //doSomething
        }

        if(key == "key_auto_reply_time"){

            val newStatus = sharedPreferences?.getString("auto_status","")
            //doSomething
        }


    }
}