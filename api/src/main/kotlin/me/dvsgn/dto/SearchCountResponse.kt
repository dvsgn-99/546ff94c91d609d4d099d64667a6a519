package me.dvsgn.dto

import me.dvsgn.search.domain.SearchCount

data class SearchCountResponse(
    val query: String,
    val count: Int
) {
    companion object {
        fun of(searchCount: SearchCount): SearchCountResponse {
            return SearchCountResponse(
                searchCount.query,
                searchCount.count
            )
        }
    }
}
