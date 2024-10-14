package me.dvsgn.place.vo

import me.dvsgn.constants.VendorType

data class SearchPlaceVo(
    private val title: String,
    private val address: String,
    private val provider: VendorType,
    private val order: Int
) {
    fun getTitle(): String = title
    fun getAddress(): String = address
    fun getProvider(): VendorType = provider
    fun getOrder(): Int = order
}
