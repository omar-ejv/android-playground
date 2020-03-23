package com.omaresli.gallery.interactors

import android.arch.lifecycle.*
import com.omaresli.gallery.repositories.CatalogRepository
import com.omaresli.gallery.routers.CatalogRouter

class CatalogInteractor(private val repository: CatalogRepository, private val router: CatalogRouter) : ViewModel() {
    private val queryLiveData: MutableLiveData<String> = MutableLiveData()
    private val pageLiveData: MutableLiveData<Int> = MutableLiveData()

    fun onLoadNextPage(lifecycleOwner: LifecycleOwner) {
        val observable = getNextPageLiveData()
        observable.observe(lifecycleOwner, Observer {
            when (it) {
                is CatalogRepository.QueryResult.SuccessResult -> router.addData(it.data)
                is CatalogRepository.QueryResult.FailureResult -> TODO()
            }
        })
    }

    fun getNextPageLiveData(): LiveData<CatalogRepository.QueryResult> {
        val currentPage = pageLiveData.value ?: 0
        pageLiveData.value = currentPage.inc()
        val query = queryLiveData.value ?: ""
        return Transformations.switchMap(pageLiveData) { index -> repository.getQuery(query, index) }
    }

}