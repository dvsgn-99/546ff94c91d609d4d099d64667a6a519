package me.dvsgn.place.configuration

import org.apache.commons.text.similarity.LevenshteinDistance
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LevenshteinDistanceConfiguration {
    @Bean
    fun levenshteinDistance(): LevenshteinDistance {
        return LevenshteinDistance()
    }
}