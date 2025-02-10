package com.enoch02.countryinfo.model

import com.google.gson.annotations.SerializedName

data class CountryData(
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val capital: String,
    val iso2: String,
    val iso3: String,
    val covid19: Covid19Data,
    @SerializedName("current_president")
    val currentPresident: PresidentData,
    val currency: String,
    @SerializedName("phone_code")
    val phoneCode: String,
    val continent: String,
    val description: String,
    val size: String,
    @SerializedName("independence_date")
    val independenceDate: String,
    val population: String,
    val href: CountryHref,
)

data class Covid19Data(
    @SerializedName("total_case")
    val totalCase: String,
    @SerializedName("total_deaths")
    val totalDeaths: String,
    @SerializedName("last_updated")
    val lastUpdated: String,
)

data class PresidentData(
    val name: String,
    val gender: String,
    @SerializedName("appointment_start_date")
    val appointmentStartDate: String,
    @SerializedName("appointment_end_date")
    val appointmentEndDate: String?,
    val href: PresidentHref,
)

data class CountryHref(
    val self: String,
    val states: String,
    val presidents: String,
    val flag: String,
)

data class PresidentHref(
    val self: String,
    val country: String,
    val picture: String,
)