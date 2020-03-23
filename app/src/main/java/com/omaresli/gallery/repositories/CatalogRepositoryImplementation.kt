package com.omaresli.gallery.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.omaresli.gallery.network.CatalogClient
import com.omaresli.gallery.network.OkHttpCatalogClient

class CatalogRepositoryImplementation : CatalogRepository {
    private val client = OkHttpCatalogClient()

    override fun getQuery(query: String, pageIndex: Int): LiveData<CatalogRepository.QueryResult> {
        val networkLiveData = client.getCatalogQueryList(query, pageIndex)
        return Transformations.map(networkLiveData) { it.mapToQueryResult() }
    }

    fun CatalogClient.QueryListResponse.mapToQueryResult(): CatalogRepository.QueryResult {
        return when (this) {
            is CatalogClient.QueryListResponse.SuccessResponse -> TODO()
            is CatalogClient.QueryListResponse.FailureResponse -> TODO()
        }
    }

}