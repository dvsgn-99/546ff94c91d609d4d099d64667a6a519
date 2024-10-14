package me.dvsgn.search.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NaverSearchPlaceResponseDto(
    @JsonProperty("lastBuildDate") val lastBuildDate: String,
    @JsonProperty("total") val total: Int,
    @JsonProperty("start") val start: Int,
    @JsonProperty("display") val display: Int,
    @JsonProperty("items") val items: List<Item>,
)

data class Item(
    @JsonProperty("title") val title: String,
    @JsonProperty("link") val link: String?,
    @JsonProperty("category") val category: String,
    @JsonProperty("description") val description: String,
    @JsonProperty("telephone") val telephone: String,
    @JsonProperty("address") val address: String,
    @JsonProperty("roadAddress") val roadAddress: String,
    @JsonProperty("mapx") val mapX: String,
    @JsonProperty("mapy") val mapY: String,
)