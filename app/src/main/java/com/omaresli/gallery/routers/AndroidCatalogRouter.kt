package com.omaresli.gallery.routers

import android.content.Context
import com.omaresli.gallery.TrackingManager
import com.omaresli.gallery.adapters.CatalogAdapter
import com.omaresli.gallery.viewmodels.RowViewModel

class AndroidCatalogRouter(
        private val adapter: CatalogAdapter,
        private val context: Context
) : CatalogRouter {

    init {
        TrackingManager.init(context)
    }

    override fun addData(data: List<RowViewModel>) {
        adapter.addItems(data)
    }

}
