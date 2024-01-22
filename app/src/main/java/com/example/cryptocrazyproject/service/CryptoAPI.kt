package com.example.cryptocrazyproject.service

import com.example.cryptocrazyproject.model.Crypto
import com.example.cryptocrazyproject.model.CryptoList
import com.example.cryptocrazyproject.model.CryptoListItem
import retrofit2.http.GET

interface CryptoAPI {

    @GET("cryptolist.json")
    suspend fun getCryptoList() : CryptoList

    @GET("crypto.json")
    suspend fun getCrypto() : Crypto
}