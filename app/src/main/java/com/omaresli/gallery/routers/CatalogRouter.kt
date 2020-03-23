package com.omaresli.gallery.routers

import com.omaresli.gallery.viewmodels.RowViewModel

interface CatalogRouter {
    fun addData(data: List<RowViewModel>)
}