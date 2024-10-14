package me.dvsgn.search.document

import me.dvsgn.search.domain.SearchCount
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "search_count")
class SearchCountDoc(
    var query: String,
    var count: Int,
) {
    fun toDomain(): SearchCount {
        return SearchCount(
            this.query,
            this.count
        )
    }
}