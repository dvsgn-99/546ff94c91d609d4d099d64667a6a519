package me.dvsgn.place.domain

import me.dvsgn.constants.VendorType
import me.dvsgn.place.vo.SearchPlaceVo

interface PlaceSorter {
    fun sortPlaces(placeMap: Map<VendorType, List<Place>>, similarityChecker: SimilarityChecker): List<SearchPlaceVo>
}