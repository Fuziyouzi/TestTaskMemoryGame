package com.example.wwatesttask.data


import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteConfig @Inject constructor() : Config {
    private val remoteConfig = Firebase.remoteConfig
    private val configSettings = remoteConfigSettings {
        minimumFetchIntervalInSeconds = 0
    }

    override suspend fun getConfig(): Boolean {
        remoteConfig.setConfigSettingsAsync(configSettings)
        withContext(Dispatchers.IO) {

            remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
                override fun onUpdate(configUpdate: ConfigUpdate) {
                    if (configUpdate.updatedKeys.contains("remote_config")) {
                        remoteConfig.activate().addOnCompleteListener {
                            remoteConfig.getBoolean("remote_config")
                        }
                    }
                }

                override fun onError(error: FirebaseRemoteConfigException) {

                }
            })
        }
        val bol = remoteConfig.getBoolean("remote_config")
        return bol
    }
}