package net.newstyleservice.example.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.newstyleservice.example.api.ApiService
import net.newstyleservice.example.api.Shikure
import ss_n.common_ktx.observer.Event
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiService: ApiService

    private val shikureList: MutableLiveData<Event<MutableList<Shikure>>> =
        MutableLiveData<Event<MutableList<Shikure>>>()

    fun getShikureList(): LiveData<Event<MutableList<Shikure>>> = shikureList

    fun requestApi() = viewModelScope.launch {

        val result = apiService.getShikure()

        shikureList.postValue(Event(result ?: mutableListOf()))
    }
}
