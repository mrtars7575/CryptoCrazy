package com.example.cryptocrazyproject.repository

import com.example.cryptocrazyproject.model.Crypto
import com.example.cryptocrazyproject.model.CryptoList
import com.example.cryptocrazyproject.util.Resource

interface CryptoRepositoryInterface  {

    suspend fun getCryptoList() : Resource<CryptoList>

    suspend fun getCrypto(id : String) : Resource<Crypto>
}