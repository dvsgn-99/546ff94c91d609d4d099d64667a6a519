package me.dvsgn.search.repository

import me.dvsgn.search.domain.SearchCount

interface SearchCountRepository {
    fun count(query: String)

    fun findTop10SearchKeywords(): List<SearchCount>
}