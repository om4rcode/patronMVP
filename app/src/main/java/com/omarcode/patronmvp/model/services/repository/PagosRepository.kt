package com.omarcode.patronmvp.model.services.repository

import com.omarcode.patronmvp.helpers.Constants
import com.omarcode.patronmvp.helpers.getRetrofitClient
import com.omarcode.patronmvp.helpers.log
import com.omarcode.patronmvp.model.interactor.PagosInteractor
import com.omarcode.patronmvp.model.pojos.ArtistItem
import com.omarcode.patronmvp.model.services.ApiService
import com.omarcode.patronmvp.mvp.PagosMVP
import retrofit2.Callback
import com.omarcode.patronmvp.model.pojos.Response
import com.omarcode.patronmvp.model.pojos.error.ApiError
import retrofit2.Call
import java.io.IOException
import kotlin.math.log

class PagosRepository(interactor : PagosInteractor) : PagosMVP.Repository {

    private var interactor : PagosMVP.Interactor? = null

    init {
        this.interactor = interactor
    }
    override fun getList() {
        val api = getRetrofitClient(Constants.BASE_URL).create(ApiService::class.java)
        val call = api.getArtists()
        call.enqueue(object : Callback<Response>{

            override fun onFailure(call: Call<Response>?, t: Throwable?) {
                log(t?.localizedMessage!!)
            }

            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
                if(!response!!.isSuccessful) {
                    if(response.errorBody()!!
                                    .contentType()!!
                                    .subtype()
                                    .equals("json")) {
                        var message = ApiError.fromResponseBody(response.errorBody()!!)
                        log("aquiii"+message!!)
                    } else {
                        try {
                            log(response.errorBody()!!)
                        } catch (ioe : IOException) {
                            log(ioe.message!!)
                        }
                    }
                    return;
                }
                // si todo fue bien
                val lista = ArrayList<ArtistItem> ()
                var artistas = response.body()?.topartists?.artist
                for(a in artistas.orEmpty()) {
                    lista.add(a!!)
                }
                interactor?.showList(lista)
            }

        })
    }

}