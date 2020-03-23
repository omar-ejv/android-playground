package com.omaresli.gallery.repositories

import android.arch.lifecycle.LiveData
import com.omaresli.gallery.viewmodels.ImageRowViewModel

interface CatalogRepository {
    fun getQuery(query: String, pageIndex: Int): LiveData<QueryResult>

    sealed class QueryResult {
        class SuccessResult(val data: List<ImageRowViewModel>) : QueryResult()
        class FailureResult(val type: FailureResultType) : QueryResult()
    }

    sealed class FailureResultType {
        class GeneralError : FailureResultType()
    }
}