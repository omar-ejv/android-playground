package com.omaresli.gallery.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Photo {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("owner")
    @Expose
    var owner: String? = null
    @SerializedName("secret")
    @Expose
    var secret: String? = null
    @SerializedName("server")
    @Expose
    var server: String? = null
    @SerializedName("farm")
    @Expose
    var farm: Int = 0
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("ispublic")
    @Expose
    var ispublic: Int = 0
    @SerializedName("isfriend")
    @Expose
    var isfriend: Int = 0
    @SerializedName("isfamily")
    @Expose
    var isfamily: Int = 0
}