package com.omaresli.gallery.routers

import com.omaresli.gallery.repositories.CatalogRepository
import com.omaresli.gallery.viewmodels.RowViewModel

interface CatalogRouter {
    fun replaceData(data: List<RowViewModel>)
    fun addData(data: List<RowViewModel>)
    fun showError(failureResultType: CatalogRepository.FailureResultType)
}