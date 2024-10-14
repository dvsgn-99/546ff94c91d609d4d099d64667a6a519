package me.dvsgn.place.domain

import org.apache.commons.text.similarity.LevenshteinDistance
import org.springframework.stereotype.Component
import kotlin.math.abs

@Component
class DefaultSimilarityChecker(
    private val levenshtein: LevenshteinDistance
) : SimilarityChecker {
    override fun isSimilar(place1: Place, place2: Place): Boolean {
        // title 유사도 계산
        val titleDistance = levenshtein.apply(place1.title, place2.title)
        val titleSimilarity = 1.0 - titleDistance.toDouble() / maxOf(place1.title.length, place2.title.length)

        // 좌표 유사도 계산
        val coordinatesSimilarity = calculateCoordinateSimilarity(place1.x, place1.y, place2.x, place2.y)

        // title 70%, 좌표 30% 가중치로 결합
        val combinedSimilarity = (titleSimilarity * 0.7) + (coordinatesSimilarity * 0.3)

        // 결합된 유사도가 0.7 이상이면 동일한 장소로 간주
        return combinedSimilarity > 0.7
    }

    // 좌표 유사도 계산: 유사도는 1.0에 가까울수록 유사한 것으로 간주
    private fun calculateCoordinateSimilarity(x1: Double, y1: Double, x2: Double, y2: Double): Double {
        val threshold = 0.001 // 좌표 차이가 0.001 이내면 동일한 장소로 간주
        val diffX = abs(x1 - x2)
        val diffY = abs(y1 - y2)

        // 좌표 차이가 임계값 내에 있으면 유사도로 1.0을 반환, 아니면 0.0을 반환
        return if (diffX < threshold && diffY < threshold) 1.0 else 0.0
    }
}