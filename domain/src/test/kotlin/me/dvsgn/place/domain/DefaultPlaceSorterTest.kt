package me.dvsgn.place.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import me.dvsgn.constants.VendorType
import org.apache.commons.text.similarity.LevenshteinDistance

class DefaultPlaceSorterTest : DescribeSpec({

    val similarityChecker = DefaultSimilarityChecker(LevenshteinDistance())
    val placeSorter = DefaultPlaceSorter()

    describe("DefaultPlaceSorter") {
        it("MIXED -> KAKAO -> NAVER 순으로 정렬한다.") {
            val kakaoPlaces = listOf(
                Place("카카오뱅크", "서울 중구", 126.981, 37.566),
                Place("카카오뱅크 고객센터", "서울 중구", 126.982, 37.567),
                Place("카카오뱅크 AI센터", "서울 중구", 126.983, 37.568)
            )
            val naverPlaces = listOf(
                Place("카카오뱅크", "서울특별시 중구", 126.981, 37.566),
                Place("카카오뱅크 고객센터", "서울특별시 중구", 126.982, 37.567),
                Place("신한은행 카카오지점", "서울특별시 중구", 126.984, 37.569)
            )

            val placeMap = mapOf(
                VendorType.KAKAO to kakaoPlaces,
                VendorType.NAVER to naverPlaces
            )

            val sortedPlaces = placeSorter.sortPlaces(placeMap, similarityChecker)

            sortedPlaces shouldHaveSize 4
            sortedPlaces[0].getProvider() shouldBe VendorType.MIXED
            sortedPlaces[1].getProvider() shouldBe VendorType.MIXED
            sortedPlaces[2].getProvider() shouldBe VendorType.KAKAO
            sortedPlaces[3].getProvider() shouldBe VendorType.NAVER
        }

        it("결과 값이 전체적으로 10개 이하일 경우, 중복을 제외하고 정렬 순서에 맞게 노출시킨다.") {
            val kakaoPlaces = listOf(
                Place("카카오뱅크", "서울 중구", 126.981, 37.566),
                Place("카카오뱅크 고객센터", "서울 중구", 126.982, 37.567)
            )
            val naverPlaces = listOf(
                Place("카카오뱅크", "서울특별시 중구", 126.981, 37.566)
            )

            val placeMap = mapOf(
                VendorType.KAKAO to kakaoPlaces,
                VendorType.NAVER to naverPlaces
            )

            val sortedPlaces = placeSorter.sortPlaces(placeMap, similarityChecker)

            sortedPlaces shouldHaveSize 2
            sortedPlaces[0].getProvider() shouldBe VendorType.MIXED
            sortedPlaces[1].getProvider() shouldBe VendorType.KAKAO
        }

        it("검색 결과가 10개 이상일 경우, 결과 값을 10개까지만 나타낸다.") {
            val kakaoPlaces = kakaoPlacesFixture()
            val naverPlaces = naverPlacesFixture()

            val placeMap = mapOf(
                VendorType.KAKAO to kakaoPlaces,
                VendorType.NAVER to naverPlaces
            )

            val sortedPlaces = placeSorter.sortPlaces(placeMap, similarityChecker)

            sortedPlaces shouldHaveSize 10
        }
    }
})

private fun naverPlacesFixture() = listOf(
    Place(
        title = "SBI저축은행 종로지점",
        address = "서울특별시 종로구 청계천로 41 3층",
        x = 126.9822308,
        y = 37.5695788
    ),
    Place(
        title = "우리은행 365 서울시청별관 2동",
        address = "서울특별시 중구 덕수궁길 15",
        x = 126.9747069,
        y = 37.5645985
    ),
    Place(
        title = "신한 서소문2청사 ATM 점두365",
        address = "서울특별시 중구 서소문로 124",
        x = 126.9752273,
        y = 37.5631065
    ),
    Place(
        title = "신한은행 을지로지점",
        address = "서울특별시 중구 을지로 109",
        x = 126.9902081,
        y = 37.5664308
    ),
    Place(
        title = "명동새마을금고 본점",
        address = "서울특별시 중구 퇴계로20길 2",
        x = 126.9856261,
        y = 37.5596006
    )
)

private fun kakaoPlacesFixture() = listOf(
    Place(
        title = "하나은행 본점",
        address = "서울 중구 을지로 35",
        x = 126.981866951611,
        y = 37.566491371702
    ),
    Place(
        title = "우리은행 본점",
        address = "서울 중구 소공로 51",
        x = 126.98175547982937,
        y = 37.55944556203258
    ),
    Place(
        title = "KB국민은행 범일동종합금융센터",
        address = "부산 동구 범일로102번길 8",
        x = 129.06038973967406,
        y = 35.138851026316075
    ),
    Place(
        title = "NH농협은행 본점영업부",
        address = "서울 중구 통일로 120",
        x = 126.96762569044095,
        y = 37.565769330640116
    ),
    Place(
        title = "하나은행 수원지점",
        address = "경기 수원시 팔달구 중부대로 32",
        x = 127.02035289108272,
        y = 37.274985485913454
    ),
    Place(
        title = "KB국민은행 수완",
        address = "광주 광산구 임방울대로 348",
        x = 126.82439621002715,
        y = 35.1910911184041
    ),
    Place(
        title = "KB국민은행 서교동종합금융센터",
        address = "서울 마포구 양화로 147",
        x = 126.92205462400821,
        y = 37.55604881087937
    ),
    Place(
        title = "우리은행 상암DMC금융센터",
        address = "서울 마포구 월드컵북로 396",
        x = 126.88978250184262,
        y = 37.57942825175973
    ),
    Place(
        title = "KB국민은행 평택고덕",
        address = "경기 평택시 고덕중앙2로 83",
        x = 127.04554346465038,
        y = 37.04518437225115
    )
)
