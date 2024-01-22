package com.example.cryptocrazyproject.repository

import com.example.cryptocrazyproject.model.Crypto
import com.example.cryptocrazyproject.model.CryptoList
import com.example.cryptocrazyproject.service.CryptoAPI
import com.example.cryptocrazyproject.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CryptoRepository @Inject constructor(
    val api : CryptoAPI
)  : CryptoRepositoryInterface{
    override suspend fun getCryptoList(): Resource<CryptoList> {
        val response = try {
            api.getCryptoList()
        }catch (e : Exception){
            return Resource.Error(e.toString())
        }
        return  Resource.Success(response)
    }

    override suspend fun getCrypto(id: String): Resource<Crypto> {
        val response = try {
            api.getCrypto()
        } catch (e:Exception) {
            return Resource.Error(e.toString())
        }

        return Resource.Success(response)
    }

}