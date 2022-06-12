package com.example.submission2

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @field:SerializedName("items")
    val items: List<ItemUser>
)

data class ItemUser(

    @field:SerializedName("login")
    val username: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("company")
    val company: String,

    @field:SerializedName("location")
    val location: String,

    @field:SerializedName("following")
    val following: Int,

    @field:SerializedName("following_url")
    val followingUrl: String,

    @field:SerializedName("followers")
    val followers: Int,

    @field:SerializedName("followers_url")
    val followersUrl: String,

    @field:SerializedName("public_repos")
    val publicRepos: Int
)
