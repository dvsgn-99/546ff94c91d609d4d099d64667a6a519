package me.dvsgn.search.event

import me.dvsgn.search.repository.SearchCountRepository
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Async
@Component
class RequestCountEventHandler(
    private val searchCountRepository: SearchCountRepository
) {
    @EventListener(RequestCountEvent::class)
    fun consume(event: RequestCountEvent) {
        searchCountRepository.count(query = event.query)
    }
}