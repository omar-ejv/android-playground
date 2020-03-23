package com.omaresli.gallery.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.omaresli.gallery.network.models.CatalogResultModel


class OkHttpCatalogClient : CatalogClient {

    override fun getCatalogQueryList(query: String, pageIndex: Int): LiveData<CatalogClient.QueryListResponse> {
        val data = MutableLiveData<CatalogClient.QueryListResponse>()

        if (query.isEmpty()) {
            data.value = CatalogClient.QueryListResponse.SuccessResponse(CatalogResultModel().apply { photos?.photo = listOf() })
        } else {
            makeCatalogQueryFrom(query, pageIndex, data)
        }

        return data
    }

    private fun makeCatalogQueryFrom(query: String, pageIndex: Int, data: MutableLiveData<CatalogClient.QueryListResponse>) {
        TODO()
    }
}