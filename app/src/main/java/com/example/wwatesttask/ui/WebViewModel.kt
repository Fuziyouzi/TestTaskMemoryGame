package com.example.wwatesttask.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wwatesttask.domain.Repository
import com.example.wwatesttask.ui.base.LiveEvent
import com.example.wwatesttask.ui.base.MutableLiveEvent
import com.example.wwatesttask.ui.base.publishEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val urlL: MutableLiveData<String> = MutableLiveData()
    val url: LiveData<String> = urlL

    private val configL: MutableLiveEvent<Boolean> = MutableLiveData()
    val config: LiveEvent<Boolean> = configL

    fun getUrl() = viewModelScope.launch(Dispatchers.Main) {
        urlL.value = repository.getUrl()
    }

    fun getConfig() = viewModelScope.launch(Dispatchers.Main) {
        configL.publishEvent(repository.getConfig())
    }


}