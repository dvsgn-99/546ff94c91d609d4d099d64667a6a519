package me.dvsgn.search.client

import me.dvsgn.constants.VendorType
import me.dvsgn.place.domain.Place

interface SearchClient {
    fun getVendorType(): VendorType

    suspend fun searchPlace(query: String): List<Place>
}