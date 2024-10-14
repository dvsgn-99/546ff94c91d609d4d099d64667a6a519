package me.dvsgn.search.service

import me.dvsgn.place.domain.PlaceResult
import me.dvsgn.place.domain.PlaceSorter
import me.dvsgn.place.domain.SimilarityChecker
import me.dvsgn.place.vo.SearchPlaceVo
import me.dvsgn.search.domain.SearchCount
import me.dvsgn.search.repository.SearchCountRepository
import org.springframework.stereotype.Service

@Service
class SearchPlaceService(
    private val searchClientAggregator: SearchClientAggregator,
    private val placeSorter: PlaceSorter,
    private val similarityChecker: SimilarityChecker,
    private val searchCountRepository: SearchCountRepository
) {
    suspend fun search(query: String): List<SearchPlaceVo> {
        val placesByVendorType = searchClientAggregator.search(query)
        val placeResult = PlaceResult(
            placeMap = placesByVendorType,
            placeSorter = placeSorter,
            similarityChecker = similarityChecker
        )

        return placeResult.sortPlaces()
    }

    fun counts(): List<SearchCount> {
        return searchCountRepository.findTop10SearchKeywords()
    }
}