package com.omarcode.patronmvp.model.pojos.error

import com.google.gson.Gson
import okhttp3.ResponseBody
import java.io.IOException

data class ApiError(val status : Int, val message : String) {

    companion object {
        fun fromResponseBody(responseBody: ResponseBody): ApiError? {
            val gson = Gson()
            try {
                return gson.fromJson(responseBody.string(), ApiError::class.java)
            }
            catch (ioe : IOException) {
                ioe.printStackTrace()
            }
            return null
        }
    }
}