package com.omaresli.gallery.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CatalogResultModel {
    @SerializedName("photos")
    @Expose
    var photos: Photos? = null
    @SerializedName("stat")
    @Expose
    var stat: String? = null
}