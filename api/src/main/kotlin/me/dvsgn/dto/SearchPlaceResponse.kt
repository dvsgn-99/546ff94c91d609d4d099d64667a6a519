package me.dvsgn.dto

import me.dvsgn.constants.VendorType
import me.dvsgn.place.vo.SearchPlaceVo

data class SearchPlaceResponse(
    val title: String,
    val address: String,
    val vendorType: VendorType,
    val order: Int
) {
    companion object {
        fun of(vo: SearchPlaceVo): SearchPlaceResponse {
            return SearchPlaceResponse(
                title = vo.getTitle(),
                address = vo.getAddress(),
                vendorType = vo.getProvider(),
                order = vo.getOrder()
            )
        }
    }
}
