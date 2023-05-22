package com.example.wwatesttask.di

import com.example.wwatesttask.data.Config
import com.example.wwatesttask.data.Database
import com.example.wwatesttask.data.RemoteConfig
import com.example.wwatesttask.data.RemoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesDataBase(remoteDatabase: RemoteDatabase): Database = remoteDatabase

    @Provides
    fun providesConfig(remoteConfig: RemoteConfig): Config = remoteConfig

}