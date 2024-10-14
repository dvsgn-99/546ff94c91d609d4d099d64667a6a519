package me.dvsgn.place.domain

import me.dvsgn.constants.VendorType
import me.dvsgn.place.vo.SearchPlaceVo
import org.springframework.stereotype.Component

@Component
class DefaultPlaceSorter : PlaceSorter {
    override fun sortPlaces(placeMap: Map<VendorType, List<Place>>, similarityChecker: SimilarityChecker): List<SearchPlaceVo> {
        val kakaoPlaces = (placeMap[VendorType.KAKAO] ?: emptyList()).take(5)
        val naverPlaces = (placeMap[VendorType.NAVER] ?: emptyList()).take(5)

        val mixedPlaces = mutableListOf<SearchPlaceVo>()
        val kakaoOnlyPlaces = mutableListOf<SearchPlaceVo>()
        val naverOnlyPlaces = mutableListOf<SearchPlaceVo>()
        val remainingNaverPlaces = naverPlaces.toMutableList()

        // 1. 카카오 결과 기준으로 네이버 결과와 비교
        kakaoPlaces.forEachIndexed { index, kakaoPlace ->
            val matchedNaverPlace = remainingNaverPlaces.find { naverPlace ->
                similarityChecker.isSimilar(kakaoPlace, naverPlace)
            }

            if (matchedNaverPlace != null) {
                // 카카오와 네이버 모두에서 같은 장소일 때 MIXED로 설정
                mixedPlaces.add(SearchPlaceVo(kakaoPlace.title, kakaoPlace.address, VendorType.MIXED, 0))
                remainingNaverPlaces.remove(matchedNaverPlace)
            } else {
                // 카카오에만 있는 경우 KAKAO로 설정
                kakaoOnlyPlaces.add(SearchPlaceVo(kakaoPlace.title, kakaoPlace.address, VendorType.KAKAO, 0))
            }
        }

        // 2. 네이버에만 있는 장소 추가
        remainingNaverPlaces.forEachIndexed { _, naverPlace ->
            naverOnlyPlaces.add(SearchPlaceVo(naverPlace.title, naverPlace.address, VendorType.NAVER, 0))
        }

        // 3. MIXED -> KAKAO -> NAVER 순으로 정렬
        val result = (mixedPlaces + kakaoOnlyPlaces + naverOnlyPlaces).toMutableList()

        // 4. 결과가 10개 미만인 경우, 남은 자리를 카카오와 네이버 결과로 채움
        val remainingSpots = 10 - result.size
        if (remainingSpots > 0) {
            val remainingKakaoPlaces = kakaoPlaces.filter { kakaoPlace ->
                result.none { it.getTitle() == kakaoPlace.title }
            }

            val remainingNaverPlacesToAdd = naverPlaces.filter { naverPlace ->
                result.none { it.getTitle() == naverPlace.title }
            }

            val combinedRemaining = (remainingKakaoPlaces + remainingNaverPlacesToAdd).take(remainingSpots)

            combinedRemaining.forEach { place ->
                val provider = if (kakaoPlaces.contains(place)) VendorType.KAKAO else VendorType.NAVER
                result.add(SearchPlaceVo(place.title, place.address, provider, 0))
            }
        }

        // 5. order 1부터 시작하게 설정
        return result.mapIndexed { index, searchPlaceVo ->
            searchPlaceVo.copy(order = index + 1)
        }.take(10)
    }
}
