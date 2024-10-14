package me.dvsgn.search.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import me.dvsgn.constants.VendorType
import me.dvsgn.place.domain.Place
import me.dvsgn.search.client.SearchClient
import org.springframework.stereotype.Component

@Component
class SearchClientAggregator(
    private val searchClient: List<SearchClient>,
) {
    suspend fun search(query: String): Map<VendorType, List<Place>> = coroutineScope {
        return@coroutineScope searchClient.map {
            async(Dispatchers.IO) {
                it.getVendorType() to it.searchPlace(query)
            }
        }.awaitAll().toMap()
    }
}