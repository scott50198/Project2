package com.hsh.project2.model

import com.google.gson.annotations.SerializedName

class UserDetail {
    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("bio")
    var bio: String? = null

    @SerializedName("login")
    var login: String? = null

    @SerializedName("site_admin")
    var siteAdmin: Boolean = false

    @SerializedName("location")
    var location: String? = null

    @SerializedName("blog")
    var blog: String? = null
}