package me.dvsgn.web

import me.dvsgn.search.event.RequestCountEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class RequestParamCountFilter(
    private val eventPublisher: ApplicationEventPublisher
) : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val query = exchange.request.queryParams.getFirst("q")

        query?.let {
            // 이벤트 발행
            eventPublisher.publishEvent(RequestCountEvent(it))
        }

        // 다음 필터 또는 컨트롤러로 요청을 넘김
        return chain.filter(exchange)
    }
}
