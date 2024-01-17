package com.example.cryptocrazyproject.service

import retrofit2.http.GET

interface CryptoAPI {

    @GET("cryptolist.json")
    suspend fun getCryptoList() : Unit

    @GET("crypto.json")
    suspend fun getCrypto() : Unit
}