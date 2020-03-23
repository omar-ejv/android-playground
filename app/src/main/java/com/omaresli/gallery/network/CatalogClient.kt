package com.omaresli.gallery.network

import android.arch.lifecycle.LiveData
import com.omaresli.gallery.network.models.CatalogResultModel

interface CatalogClient {
    fun getCatalogQueryList(query: String, pageIndex: Int): LiveData<QueryListResponse>

    sealed class QueryListResponse {
        class SuccessResponse(val data: CatalogResultModel) : QueryListResponse()
        class FailureResponse(val type: FailureResponseType) : QueryListResponse()
    }

    sealed class FailureResponseType {
        class GeneralError : FailureResponseType()
    }
}