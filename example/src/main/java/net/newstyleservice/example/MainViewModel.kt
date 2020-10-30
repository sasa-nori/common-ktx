package net.newstyleservice.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.ss_n.common_ktx.extension.createRetrofitService
import app.ss_n.common_ktx.observer.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ss_n.common_ktx.HttpClient

class MainViewModel : ViewModel() {

    private val shikureList: MutableLiveData<Event<MutableList<Shikure>>> by lazy {
        MutableLiveData<Event<MutableList<Shikure>>>()
    }

    fun getShikureList(): LiveData<Event<MutableList<Shikure>>> = shikureList

    private val apiService: ApiService by lazy {
        URL.createRetrofitService(
            service = ApiService::class.java,
            client = HttpClient.createCustomClient()
        )
    }

    fun requestApi() = viewModelScope.launch {
        val result = withContext(Dispatchers.IO) {
            apiService.getShikure()
        }
        shikureList.postValue(Event(result ?: mutableListOf()))
    }

    companion object {
        private const val URL = "https://mhf.newstyleservice.net"
    }
}
