package com.enoch02.countryinfo.model

import com.google.gson.annotations.SerializedName

data class StateResponse(
    val data: List<StateData>,
)

data class StateData(
    val name: String,
    val iso2: String,
    @SerializedName("fips_code")
    val fipsCode: String,
    val population: String,
    val size: String?,
    @SerializedName("official_language")
    val officialLanguage: String?,
    val region: String?,
    val href: Href,
)

data class Href(
    val self: String,
    val country: String,
)
