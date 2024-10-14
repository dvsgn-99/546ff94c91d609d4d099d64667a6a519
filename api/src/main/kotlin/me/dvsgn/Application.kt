package me.dvsgn

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlaceSearchApplication

fun main(args: Array<String>) {
    runApplication<PlaceSearchApplication>(*args)
}