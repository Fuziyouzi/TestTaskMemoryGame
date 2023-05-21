package com.example.wwatesttask.di

import com.example.wwatesttask.data.RepositoryImp
import com.example.wwatesttask.domain.ListOfImages
import com.example.wwatesttask.domain.ListOfImagesImpl
import com.example.wwatesttask.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindsListOfImages(listOfImagesImpl: ListOfImagesImpl): ListOfImages

    @Binds
    fun bindsRepo(repositoryImp: RepositoryImp): Repository
}