package com.omaresli.gallery.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photos {
    @SerializedName("page")
    @Expose
    var page: Int = 0
    @SerializedName("pages")
    @Expose
    var pages: String? = null
    @SerializedName("perpage")
    @Expose
    var perpage: Int = 0
    @SerializedName("total")
    @Expose
    var total: String? = null
    @SerializedName("photo")
    @Expose
    var photo: List<Photo>? = null
}