package com.enoch02.countryinfo.model

data class Country(
    val flags: Flags,
    val coatOfArms: CoatOfArms,
    val name: Name,
    val capital: List<String>,
    val subregion: String,
    val population: Long,
    val timezones: List<String>?,
    val continents: List<String>,
)

data class Flags(
    val png: String,
    val svg: String,
    val alt: String,
)

data class CoatOfArms(
    val png: String,
    val svg: String,
)

data class Name(
    val common: String,
    val official: String,
    val nativeName: NativeName,
)

data class NativeName(
    val fra: NameTranslation,
    val gsw: NameTranslation,
    val ita: NameTranslation,
    val roh: NameTranslation,
)

data class NameTranslation(
    val official: String,
    val common: String,
)