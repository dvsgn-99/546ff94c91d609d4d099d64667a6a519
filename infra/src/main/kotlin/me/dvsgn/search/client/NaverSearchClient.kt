package me.dvsgn.search.client

import kotlinx.coroutines.reactor.awaitSingle
import me.dvsgn.constants.VendorType
import me.dvsgn.place.domain.Place
import me.dvsgn.search.dto.NaverSearchPlaceResponseDto
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class NaverSearchClient(
    private val naverWebClient: WebClient
): SearchClient {
    companion object {
        const val DISPLAY_COUNT = 5
    }

    override fun getVendorType(): VendorType {
        return VendorType.NAVER
    }

    override suspend fun searchPlace(query: String): List<Place> {
        return naverWebClient.get()
            .uri("/v1/search/local.json?query={query}&display={display}", query, DISPLAY_COUNT)
            .retrieve()
            .bodyToMono(NaverSearchPlaceResponseDto::class.java)
            .onErrorResume{ Mono.empty() }
            .awaitSingle()
            .items
            .map { searchResult ->
                Place(
                    title = searchResult.title.replace(Regex("<.*?>"), ""),
                    address = searchResult.roadAddress,
                    x = convertToDouble(searchResult.mapX),
                    y = convertToDouble(searchResult.mapY)
                )
            }
    }

    private fun convertToDouble(coordinate: String): Double {
        return coordinate.toBigDecimal().movePointLeft(7).toDouble()
    }
}