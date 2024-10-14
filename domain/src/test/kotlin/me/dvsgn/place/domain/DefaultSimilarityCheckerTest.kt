package me.dvsgn.place.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.apache.commons.text.similarity.LevenshteinDistance

class DefaultSimilarityCheckerTest : DescribeSpec({

    val similarityChecker = DefaultSimilarityChecker(LevenshteinDistance())

    describe("DefaultSimilarityChecker") {
        it("두 개의 장소가 같을 경우, true를 반환한다.") {
            val place1 = Place("카카오뱅크 본점", "서울특별시 중구", 126.981, 37.566)
            val place2 = Place("카카오뱅크 본점", "서울특별시 중구", 126.981, 37.566)

            similarityChecker.isSimilar(place1, place2) shouldBe true
        }

        it("두 개의 장소의 장소명이 비슷할 경우, true를 반환한다.") {
            val place1 = Place("카카오뱅크 본점", "서울특별시 중구", 126.9813, 37.5665)
            val place2 = Place("카카오 뱅크 본점", "서울특별시 중구", 126.9812, 37.5662)

            similarityChecker.isSimilar(place1, place2) shouldBe true
        }

        it("두 개의 장소의 좌표가 비슷할 경우, true를 반환한다.") {
            val place1 = Place("카카오뱅크 본점", "서울특별시 중구", 127.00003, 38.5660)
            val place2 = Place("카카오뱅크 본점", "서울특별시 중구", 127.00002, 38.5669)

            similarityChecker.isSimilar(place1, place2) shouldBe true
        }

        it("두 개의 장소의 좌표가 전혀 다를 경우, false를 반환한다.") {
            val place1 = Place("카카오뱅크 본점", "서울특별시 중구", 122.981, 31.566)
            val place2 = Place("카카오뱅크 본점", "서울특별시 중구", 127.000, 38.000)

            similarityChecker.isSimilar(place1, place2) shouldBe false
        }

        it("두 개의 장소의 좌표가 유사하더라도 장소명이 다를 경우, false를 반환한다.") {
            val place1 = Place("카카오뱅크 본점", "서울특별시 중구", 126.981, 37.566)
            val place2 = Place("우리은행", "서울특별시 중구", 126.982, 37.566)

            similarityChecker.isSimilar(place1, place2) shouldBe false
        }
    }
})
