package com.omaresli.gallery.interactors

import android.arch.lifecycle.*
import android.content.Context
import android.os.NetworkOnMainThreadException
import com.omaresli.gallery.adapters.CatalogAdapter
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


class CatalogViewModel(
        private val adapter: CatalogAdapter,
        private val context: Context,
        private val lifecycleOwner: LifecycleOwner
) : ViewModel() {

    init {
        // Make sure adapter object isn't null
        requireNotNull(adapter)
        TrackingManager.context = context
    }

    fun onLoadNextPage() {
        val observable = getNextPageLiveData()
        observable!!.observe(lifecycleOwner, Observer {
            when (it) {
                is Result.Success -> adapter.addItems(it.data)
            }
        })
    }

    fun getNextPageLiveData(): LiveData<Result>? {
        val client = OkHttpClient()
        val url = "https:somefancyapi.com/rest/catalog"
        val request: Request = Response.Builder()
                .url(url)
                .build()

        val data = MutableLiveData<Result.Success>()

        return try {
            client.newCall(request).execute().use { response ->
                data.value = Result.Success(response.body()!!.string())
                data as LiveData<Result>
            }
        } catch (ex: NetworkOnMainThreadException) {
            // Ignore
            null
        }
    }

    sealed class Result {
        data class Success(val data: String) : Result()
    }

    public companion object TrackingManager {
        public lateinit var context: Context

        fun track(eventLabel: String) {
            with(context) {
                tracker.sendEvent(this, eventLabel)
            }
        }
    }

}