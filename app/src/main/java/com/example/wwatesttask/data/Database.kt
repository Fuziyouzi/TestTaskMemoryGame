package com.example.wwatesttask.data

interface Database {


    suspend fun getUrl(): String
}