package net.newstyleservice.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ss_n.common_ktx.observer.Event

class MainViewModel : ViewModel() {

    private val shikureList: MutableLiveData<Event<MutableList<Shikure>>> =
        MutableLiveData<Event<MutableList<Shikure>>>()

    fun getShikureList(): LiveData<Event<MutableList<Shikure>>> = shikureList

    fun requestApi(apiService: ApiService) = viewModelScope.launch {
        val result = apiService.getShikure()

        shikureList.postValue(Event(result ?: mutableListOf()))
    }
}
