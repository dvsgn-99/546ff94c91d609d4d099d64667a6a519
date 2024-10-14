package me.dvsgn.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfiguration {
    @Value("\${api-endpoint.kakao.host}")
    lateinit var kakaoHost: String
    @Value("\${api-endpoint.kakao.auth}")
    lateinit var kakaoAuth: String

    @Value("\${api-endpoint.naver.host}")
    lateinit var naverHost: String
    @Value("\${api-endpoint.naver.auth.client-id}")
    lateinit var naverClientId: String
    @Value("\${api-endpoint.naver.auth.client-secret}")
    lateinit var naverClientSecret: String

    private fun createWebClientBuilder(baseUrl: String): WebClient.Builder {
        return WebClient.builder()
            .baseUrl(baseUrl)
    }

    @Bean
    fun kakaoWebClient(): WebClient {
        val objectMapper = ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)

        val exchangeStrategies = ExchangeStrategies.builder()
            .codecs { configurer ->
                configurer.defaultCodecs().jackson2JsonEncoder(Jackson2JsonEncoder(objectMapper))
                configurer.defaultCodecs().jackson2JsonDecoder(Jackson2JsonDecoder(objectMapper))
            }
            .build()

        return createWebClientBuilder(kakaoHost)
            .defaultHeader("Authorization", kakaoAuth)
            .exchangeStrategies(exchangeStrategies)
            .build()
    }

    @Bean
    fun naverWebClient(): WebClient {
        return createWebClientBuilder(naverHost)
            .defaultHeader("X-Naver-Client-Id", naverClientId)
            .defaultHeader("X-Naver-Client-Secret", naverClientSecret)
            .build()
    }
}