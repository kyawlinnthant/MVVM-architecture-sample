package com.klt.gbs.model.response

import com.klt.gbs.util.Resource
import retrofit2.Response

inline fun <T> safeApiCall(apiCall: () -> Response<T>): Resource<T> {

    try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body() ?: return Resource.error("EMPTY BODY")
            return Resource.success(body)
        }
        return Resource.error("ERROR CODE ${response.code()} : ${response.message()}")

    } catch (e: Exception) {
        return Resource.error(e.message ?: "Unknown Error")
    }

}
