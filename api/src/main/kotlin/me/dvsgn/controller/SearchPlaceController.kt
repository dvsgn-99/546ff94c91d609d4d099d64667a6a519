package me.dvsgn.controller

import me.dvsgn.dto.Resource
import me.dvsgn.dto.SearchCountResponse
import me.dvsgn.dto.SearchPlaceResponse
import me.dvsgn.search.service.SearchPlaceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(("/v1/places"))
class SearchPlaceController(
    private val searchPlaceService: SearchPlaceService
) {
    @GetMapping
    suspend fun searchPlace(@RequestParam(value = "q", required = true) query: String): Resource<List<SearchPlaceResponse>> {
        val response = searchPlaceService.search(query)
            .map { SearchPlaceResponse.of(it) }

        return Resource.ok(response)
    }

    @GetMapping("/counts")
    suspend fun counts(): Resource<List<SearchCountResponse>> {
        val response = searchPlaceService.counts()
            .map { SearchCountResponse.of(it) }

        return Resource.ok(response)
    }
}