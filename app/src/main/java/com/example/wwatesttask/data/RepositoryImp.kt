package com.example.wwatesttask.data


import com.example.wwatesttask.domain.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val database: Database,
    private val remoteConfig: Config
) : Repository {


    override suspend fun getUrl(): String {
        return if (!remoteConfig.getConfig()) {
            ""
        } else {
            database.getUrl()
        }


    }

    override suspend fun getConfig() = remoteConfig.getConfig()

}