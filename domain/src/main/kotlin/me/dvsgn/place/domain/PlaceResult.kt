package me.dvsgn.place.domain

import me.dvsgn.constants.VendorType
import me.dvsgn.place.vo.SearchPlaceVo

class PlaceResult(
    private val placeMap: Map<VendorType, List<Place>>,
    private val placeSorter: PlaceSorter,
    private val similarityChecker: SimilarityChecker
) {
    fun sortPlaces(): List<SearchPlaceVo> {
        return placeSorter.sortPlaces(placeMap, similarityChecker)
    }
}