package com.omarcode.patronmvp.helpers

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

inline fun FragmentManager.inTransaction(func :FragmentTransaction. () -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun ViewGroup.inflate(layoutRes : Int) : View {
    return LayoutInflater.from(context).inflate(layoutRes, this,false)
}

fun getRetrofitClient(base_url : String) : Retrofit {
        return Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
}

fun log(message : Any) {
    Log.i("RESPUESTA", message.toString())
}
