package com.example.globchat

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.preference.*


class AccountSettingsFragment : PreferenceFragmentCompat() {

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
       // setPreferencesFromResource(R.xml.account_settings, rootKey)

        var myIntent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://www.facebook.com")
        }

        val infoPrep = MultiSelectListPreference(context).apply {
            entries = resources.getStringArray(R.array.pref_profile_info)
            entryValues = resources.getStringArray(R.array.pref_profile_info_value)
            key = "public info"
            title = "My Public Info"
            isIconSpaceReserved = false
        }

        val logOut = Preference(context).apply {
            isIconSpaceReserved = false
            key = "logOut"
            title = "Log Out"
            intent = myIntent
        }

        val deleteAccount = Preference(context).apply {
            icon = ResourcesCompat.getDrawable(resources,R.drawable.ic_baseline_delete_24,null)
            title = "Delete Account"
            key = "delete_account"
            summary = "Delete can not be undo"
        }

        val privacy = PreferenceCategory(context)
        privacy.title = "Privacy"
        privacy.isIconSpaceReserved = false



        val security = PreferenceCategory(context).apply {
            title = "Security"
            isIconSpaceReserved = false

        }


        val prefScreen = preferenceManager.createPreferenceScreen(context)

        prefScreen.addPreference(privacy)
        prefScreen.addPreference(security)
        privacy.addPreference(infoPrep)

        security.addPreference(logOut)
        security.addPreference(deleteAccount)

        preferenceScreen = prefScreen

    }


}