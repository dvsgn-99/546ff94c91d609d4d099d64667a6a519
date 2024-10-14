package me.dvsgn.dto

import org.springframework.http.HttpStatus

class Resource<T>(
    val data: T?,
    val status: Int,
    val errorMessage: String? = null
) {
    companion object {
        fun <T> ok(data: T): Resource<T> {
            return Resource(data, HttpStatus.OK.value())
        }

        fun <T> error(message: String, errorStatus: HttpStatus): Resource<T> {
            return Resource(null, errorStatus.value(), message)
        }
    }
}