package com.example.globchat


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.preference.*


class SettingsFragment : PreferenceFragmentCompat() {
    val TAG = "SettingsFragment"

    @SuppressLint("ResourceType")
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)


        val dataStore = DataStore()

        /**
         * I can equally change the data storage to be use on only one Preference type
         * by calling the preference and setting the  preferenceDatastore  on the preference item
         *check line  74-75
         */

        /**
         * change the whole Fragment  storage system  from sharedPreference to datastore storage
         */

        // preferenceManager.preferenceDataStore = dataStore


        val actSystemAccount = findPreference<Preference>("key_account_settings")
        actSystemAccount?.setOnPreferenceClickListener {
            // do something
            findNavController().navigate(R.id.action_settingsFragment_to_accountSettingsFragment)
            true
        }

        val actStatus = findPreference<EditTextPreference>("key_status")
        actStatus?.setOnPreferenceChangeListener { preference, newvalue ->
            // regulate what can be a status using the return statement true for good update n bad for not to be updated

            val updateStatus = newvalue as String

            if (updateStatus.contains("bad") || updateStatus.contains("fuck")) {
                Toast.makeText(requireContext(), "No Fowl Lang", Toast.LENGTH_SHORT).show()

                false
            } else {
                Log.i(TAG, "auto status  $newvalue")
                true
            }

        }


        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)

        val autoTime = sharedPreference.getString("key_auto_reply_time", "")
        val autoDownload = sharedPreference.getBoolean("key_auto_download", false)

        Log.i(TAG, "auto key time $autoTime")
        Log.i(TAG, "auto key download $autoDownload")

        val notificationSettings = findPreference<SwitchPreferenceCompat>("key_new_message_info")
        notificationSettings?.summaryProvider = Preference.SummaryProvider<SwitchPreferenceCompat> {
            if (it.isChecked) "updated " else "unUpdated"
        }

        //data store assignment
      //  notificationSettings?.preferenceDataStore = dataStore

    }


    class DataStore : PreferenceDataStore() {
        /**
         * I am using getBoolean because the notification has a SwitchPreferenceCompat and its a Boolean Parameter
         * if i have a stated others to use the datastore, i would have override other methods like putString and getString
         */

        override fun getBoolean(key: String?, defValue: Boolean): Boolean {
            if(key == "key_new_message_info"){
                //return the value from any where you have saved it
            }

            return defValue
        }

        override fun putBoolean(key: String?, value: Boolean) {
           if(key == "key_new_message_info"){
               //save to anywhere you like
           }
        }
    }


}