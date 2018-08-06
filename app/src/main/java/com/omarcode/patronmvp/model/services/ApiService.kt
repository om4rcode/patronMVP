package com.omarcode.patronmvp.model.services

import com.omarcode.patronmvp.model.pojos.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("2.0")
    fun getArtists(@Query("method") _method : String = "geo.gettopartists"
                   ,@Query("country") _country : String = "peru"
                   ,@Query("api_key") _apy_key : String ="4726f212ab23eaed45ab64e046af7f1f"
                   ,@Query("format") _format : String = "json") : Call<Response>

}