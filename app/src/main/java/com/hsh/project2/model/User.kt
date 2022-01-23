package com.hsh.project2.model

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("login")
    var login: String? = null

    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("site_admin")
    var siteAdmin: Boolean = false


}