package com.enoch02.countryinfo.model

import com.google.gson.annotations.SerializedName

data class CountryApiResponse(
    val data: List<CountryData>,
    val links: Links,
    val meta: Meta,
)

data class Links(
    val first: String,
    val last: String,
    val prev: String?,
    val next: String?,
)

data class Meta(
    @SerializedName("current_page")
    val currentPage: Int,
    val from: Int,
    @SerializedName("last_page")
    val lastPage: Int,
    val path: String,
    @SerializedName("per_page")
    val perPage: Int,
    val to: Int,
    val total: Int,
)