package com.example.wwatesttask.domain

interface Repository {

   suspend fun getUrl(): String

   suspend fun getConfig(): Boolean
}