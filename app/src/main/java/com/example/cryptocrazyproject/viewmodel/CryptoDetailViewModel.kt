package com.example.cryptocrazyproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cryptocrazyproject.model.Crypto
import com.example.cryptocrazyproject.repository.CryptoRepository
import com.example.cryptocrazyproject.repository.CryptoRepositoryInterface
import com.example.cryptocrazyproject.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    val repository: CryptoRepositoryInterface
)  : ViewModel(){

    suspend fun getCrypto(id :String):Resource<Crypto> {
        return repository.getCrypto(id)
    }
}