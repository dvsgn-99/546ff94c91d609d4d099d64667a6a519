package me.dvsgn.place.domain

interface SimilarityChecker {
    fun isSimilar(place1: Place, place2: Place): Boolean
}