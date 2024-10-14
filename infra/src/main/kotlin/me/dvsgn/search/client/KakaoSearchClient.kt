package me.dvsgn.search.client

import kotlinx.coroutines.reactor.awaitSingle
import me.dvsgn.constants.VendorType
import me.dvsgn.place.domain.Place
import me.dvsgn.search.dto.KakaoSearchPlaceResponseDto
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class KakaoSearchClient(
    private val kakaoWebClient: WebClient,
) : SearchClient {
    override fun getVendorType(): VendorType {
        return VendorType.KAKAO
    }

    override suspend fun searchPlace(query: String): List<Place> {
        return kakaoWebClient.get()
            .uri("/v2/local/search/keyword.json?query={query}", query)
            .retrieve()
            .bodyToMono(KakaoSearchPlaceResponseDto::class.java)
            .onErrorResume{ Mono.empty() }
            .awaitSingle()
            .documents
            .map {
                Place(
                    title = it.placeName,
                    address = it.roadAddressName,
                    x = it.x.toDouble(),
                    y = it.y.toDouble(),
                )
            }
    }
}